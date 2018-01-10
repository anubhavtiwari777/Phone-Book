import com.vadimksn.phonebook.data.entity.Contact;
import com.vadimksn.phonebook.exception.ErrorReason;
import com.vadimksn.phonebook.exception.model.BadRequestException;
import com.vadimksn.phonebook.utils.ValidationUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestValidationUtils {
    @Mock
    private ValidationUtils mockedValidationUtils;
    private final Contact VALID_CONTACT = Contact.builder()
            .name("Name").address("Country, city ,street").newPhoneNumber("0630001122").build();
    private final Contact NOT_VALID_CONTACT = Contact.builder()
            .name("A").address("123456789112345678921234567891236545").newPhoneNumber("aa").build();

    @Before
    public void init() {
        when(mockedValidationUtils.isNotNull(VALID_CONTACT.getName())).thenReturn(true);
        when(mockedValidationUtils.isNotNull(VALID_CONTACT.getAddress())).thenReturn(true);
        when(mockedValidationUtils.isNotNull(VALID_CONTACT.getNewPhoneNumber())).thenReturn(true);
        when(mockedValidationUtils.validateName(VALID_CONTACT.getName())).thenReturn(true);
        when(mockedValidationUtils.validateName(NOT_VALID_CONTACT.getName())).thenThrow(new BadRequestException(
                ErrorReason.NAME_IS_LESS_THAN_2, NOT_VALID_CONTACT.getName()));
        when(mockedValidationUtils.validatePhoneNumber(VALID_CONTACT.getNewPhoneNumber())).thenReturn(true);
        when(mockedValidationUtils.validatePhoneNumber(NOT_VALID_CONTACT.getNewPhoneNumber())).thenThrow(new BadRequestException(
                ErrorReason.NOT_VALID_PHONE_NUMBER, NOT_VALID_CONTACT.getNewPhoneNumber()));
        when(mockedValidationUtils.validateAddress(VALID_CONTACT.getAddress())).thenReturn(true);
        when(mockedValidationUtils.validateAddress(NOT_VALID_CONTACT.getAddress())).thenThrow(new BadRequestException(
                ErrorReason.ADDRESS_IS_MORE_THAN_30, NOT_VALID_CONTACT.getAddress()));
    }

    @Test
    public void testNullValidator() {
        assertEquals(true, mockedValidationUtils.isNotNull(VALID_CONTACT.getName()));
        assertEquals(true, mockedValidationUtils.isNotNull(VALID_CONTACT.getAddress()));
        assertEquals(true, mockedValidationUtils.isNotNull(VALID_CONTACT.getNewPhoneNumber()));
    }

    @Test(expected = BadRequestException.class)
    public void testValidateName() {
        assertEquals(true, mockedValidationUtils.validateName(VALID_CONTACT.getName()));
        //Throws BadRequestException(ErrorReason.NAME_IS_LESS_THAN_2)
        mockedValidationUtils.validateName(NOT_VALID_CONTACT.getName());
    }

    @Test(expected = BadRequestException.class)
    public void testValidatePhoneNumber() {
        assertEquals(true, mockedValidationUtils.validatePhoneNumber(VALID_CONTACT.getNewPhoneNumber()));
        //Throws BadRequestException(ErrorReason.NOT_VALID_PHONE_NUMBER)
        mockedValidationUtils.validatePhoneNumber(NOT_VALID_CONTACT.getNewPhoneNumber());
    }

    @Test(expected = BadRequestException.class)
    public void testValidateAddress() {
        assertEquals(true, mockedValidationUtils.validateAddress(VALID_CONTACT.getAddress()));
        //Throws BadRequestException(ErrorReason.ADDRESS_IS_MORE_THAN_30)
        mockedValidationUtils.validateAddress(NOT_VALID_CONTACT.getAddress());
    }
}

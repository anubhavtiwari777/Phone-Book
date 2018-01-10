import com.vadimksn.phonebook.data.entity.Contact;
import com.vadimksn.phonebook.data.entity.PhoneNumber;
import com.vadimksn.phonebook.utils.FilterContactListUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestFilterContactListUtils {
    @Mock
    private FilterContactListUtils mockFilterContactListUtils;
    private final String STR1 = "smith";
    private final String STR2 = "OLOLO";
    private final String STR3 = "1";
    private final String STR4 = "gaLYna";
    private final String STR5 = "strEEt";
    private final Contact CONTACT_1 = Contact.builder()
            .name("Bob Smith").address("Baker street").phoneNumbers(Arrays.asList(
                    PhoneNumber.builder().phoneNumber("1111111").build(),
                    PhoneNumber.builder().phoneNumber("2222").build(),
                    PhoneNumber.builder().phoneNumber("3333").build())).build();
    private final Contact CONTACT_2 = Contact.builder()
            .name("John Smith").address("Baker street").phoneNumbers(Arrays.asList(
                    PhoneNumber.builder().phoneNumber("1111111").build(),
                    PhoneNumber.builder().phoneNumber("12332").build())).build();
    private final Contact CONTACT_3 = Contact.builder()
            .name("Lola Smith").address("Baker street").phoneNumbers(Arrays.asList(
                    PhoneNumber.builder().phoneNumber("1111111").build(),
                    PhoneNumber.builder().phoneNumber("777777").build())).build();
    private final Contact CONTACT_4 = Contact.builder()
            .name("Karas Mokryi").address("River street").phoneNumbers(Arrays.asList(
                    PhoneNumber.builder().phoneNumber("043255555").build())).build();
    private final Contact CONTACT_5 = Contact.builder()
            .name("Galyna Stepanivna").address("Lenin street").phoneNumbers(Arrays.asList(
                    PhoneNumber.builder().phoneNumber("112").build(),
                    PhoneNumber.builder().phoneNumber("911").build())).build();
    private final List<Contact> CONTACT_LIST = Arrays.asList(CONTACT_1, CONTACT_2, CONTACT_3, CONTACT_4, CONTACT_5);

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        when(mockFilterContactListUtils.filter(STR1, CONTACT_LIST)).thenReturn(Arrays.asList(CONTACT_1, CONTACT_2, CONTACT_3));
        when(mockFilterContactListUtils.filter(STR2, CONTACT_LIST)).thenReturn(new ArrayList());
        when(mockFilterContactListUtils.filter(STR3, CONTACT_LIST)).thenReturn(Arrays.asList(CONTACT_1, CONTACT_2, CONTACT_3, CONTACT_5));
        when(mockFilterContactListUtils.filter(STR4, CONTACT_LIST)).thenReturn(Arrays.asList(CONTACT_5));
        when(mockFilterContactListUtils.filter(STR5, CONTACT_LIST)).thenReturn(Arrays.asList(CONTACT_1, CONTACT_2, CONTACT_3, CONTACT_4, CONTACT_5));
    }

    @Test
    public void testFilter1() {
        assertEquals(Arrays.asList(CONTACT_1, CONTACT_2, CONTACT_3), mockFilterContactListUtils.filter(STR1, CONTACT_LIST));
    }

    @Test
    public void testFilter2() {
        assertEquals(new ArrayList(), mockFilterContactListUtils.filter(STR2, CONTACT_LIST));
    }

    @Test
    public void testFilter3() {
        assertEquals(Arrays.asList(CONTACT_1, CONTACT_2, CONTACT_3, CONTACT_5), mockFilterContactListUtils.filter(STR3, CONTACT_LIST));
    }

    @Test
    public void testFilter4() {
        assertEquals(Arrays.asList(CONTACT_5), mockFilterContactListUtils.filter(STR4, CONTACT_LIST));
    }

    @Test
    public void testFilter5() {
        assertEquals(Arrays.asList(CONTACT_1, CONTACT_2, CONTACT_3, CONTACT_4, CONTACT_5), mockFilterContactListUtils.filter(STR5, CONTACT_LIST));
    }
}

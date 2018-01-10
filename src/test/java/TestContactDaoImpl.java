import com.vadimksn.phonebook.data.dao.ContactDaoImpl;
import com.vadimksn.phonebook.data.entity.Contact;
import com.vadimksn.phonebook.data.entity.PhoneNumber;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestContactDaoImpl {
    @Mock
    private ContactDaoImpl mockedContactDao;
    private final Contact CONTACT_1 = Contact.builder()
            .id(1).name("First").address("1st address").newPhoneNumber("1111111111").phoneNumbers(Arrays.asList(
                    PhoneNumber.builder().id(1).phoneNumber("1111111112").build(),
                    PhoneNumber.builder().id(1).phoneNumber("1111111113").build(),
                    PhoneNumber.builder().id(1).phoneNumber("1111111114").build())).build();
    private final Contact CONTACT_2 = Contact.builder()
            .id(2).name("Second").address("2nd address").newPhoneNumber("2222222222").phoneNumbers(Arrays.asList(
                    PhoneNumber.builder().id(2).phoneNumber("2222222222").build(),
                    PhoneNumber.builder().id(2).phoneNumber("2222222222").build())).build();
    private final String NAME_1 = CONTACT_1.getName();
    private final String ADDRESS_1 = CONTACT_1.getAddress();
    private final String NEW_PHONE_NUMBER_1 = CONTACT_1.getNewPhoneNumber();
    private final String NAME_2 = CONTACT_2.getName();
    private final String ADDRESS_2 = CONTACT_2.getAddress();
    private final String NEW_PHONE_NUMBER_2 = CONTACT_2.getNewPhoneNumber();
    private final String FILTER_1 = "2";
    private final String FILTER_2 = CONTACT_1.getName();
    private final String FILTER_3 = "Ololo";

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        when(mockedContactDao.getListAllContacts()).thenReturn(Arrays.asList(CONTACT_1, CONTACT_2));
        when(mockedContactDao.addContact(CONTACT_2)).thenReturn(CONTACT_2);
        when(mockedContactDao.updateAddressAddPhone(CONTACT_1)).thenReturn(CONTACT_1);
        when(mockedContactDao.isExistContact(CONTACT_1)).thenReturn(true);
        when(mockedContactDao.getListContactsFilteredByString(FILTER_1)).thenReturn(Arrays.asList(CONTACT_1, CONTACT_2));
        when(mockedContactDao.getListContactsFilteredByString(FILTER_2)).thenReturn(Collections.singletonList(CONTACT_1));
        when(mockedContactDao.getListContactsFilteredByString(FILTER_3)).thenReturn(new ArrayList<>());
    }

    @Test
    public void testGetListAllContacts() {
        List<Contact> allContacts = mockedContactDao.getListAllContacts();
        Contact myContact = allContacts.get(0);
        assertEquals(2, allContacts.size());
        assertEquals(1, myContact.getId());
        assertEquals(NAME_1, myContact.getName());
        assertEquals(ADDRESS_1, myContact.getAddress());
        assertEquals(NEW_PHONE_NUMBER_1, myContact.getNewPhoneNumber());
        assertEquals(3, myContact.getPhoneNumbers().size());
    }

    @Test
    public void testIsExistContact() {
        assertEquals(true, mockedContactDao.isExistContact(CONTACT_1));
    }

    @Test
    public void testAddContact() {
        Contact myContact = mockedContactDao.addContact(CONTACT_2);
        assertNotNull(myContact);
        assertEquals(2, myContact.getId());
        assertEquals(NAME_2, myContact.getName());
        assertEquals(ADDRESS_2, myContact.getAddress());
        assertEquals(NEW_PHONE_NUMBER_2, myContact.getNewPhoneNumber());
        assertEquals(2, myContact.getPhoneNumbers().size());
    }

    @Test
    public void testUpdateAddressAddPhone() {
        Contact myContact = mockedContactDao.updateAddressAddPhone(CONTACT_1);
        assertNotNull(myContact);
        assertEquals(1, myContact.getId());
        assertEquals(NAME_1, myContact.getName());
        assertEquals(ADDRESS_1, myContact.getAddress());
        assertEquals(NEW_PHONE_NUMBER_1, myContact.getNewPhoneNumber());
        assertEquals(3, myContact.getPhoneNumbers().size());
    }

    @Test
    public void testFilteredContacts1() {
        List<Contact> list = mockedContactDao.getListContactsFilteredByString(FILTER_1);
        assertEquals(2, list.size());
        assertEquals(true,list.containsAll(Arrays.asList(CONTACT_1,CONTACT_2)));
    }

    @Test
    public void testFilteredContacts2() {
        List<Contact> list = mockedContactDao.getListContactsFilteredByString(FILTER_2);
        assertEquals(1, list.size());
        assertEquals(false,list.contains(CONTACT_2));
    }

    @Test
    public void testFilteredContacts3() {
        List<Contact> list = mockedContactDao.getListContactsFilteredByString(FILTER_3);
        assertEquals(0, list.size());
    }
}

package com.vadimksn.phonebook.services;

import com.vadimksn.phonebook.data.dao.ContactDao;
import com.vadimksn.phonebook.data.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("contactService")
public class ContactServiceImpl implements ContactService<Contact> {

    @Autowired
    ContactDao<Contact> contactDao;

    @Override
    public Contact addContact(Contact contact) {
        return contactDao.addContact(contact);
    }

    @Override
    public Contact updateAddressAddPhone(Contact contact) {
        return contactDao.updateAddressAddPhone(contact);
    }

    @Override
    public boolean isExistContact(Contact contact) {
        return contactDao.isExistContact(contact);
    }

    @Override
    public List<Contact> getListAllContacts() {
        return contactDao.getListAllContacts();
    }

    @Override
    public List<Contact> getListContactsFilteredByString(String s) {
        return contactDao.getListContactsFilteredByString(s);
    }
}

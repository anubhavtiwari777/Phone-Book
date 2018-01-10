package com.vadimksn.phonebook.web;

import com.vadimksn.phonebook.data.entity.Contact;
import com.vadimksn.phonebook.services.ContactService;
import com.vadimksn.phonebook.utils.FilterContactListUtils;
import com.vadimksn.phonebook.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class PhoneBookController {

    @Autowired
    ContactService<Contact> contactService;

    @Autowired
    FilterContactListUtils filterContactListUtils;

    @Autowired
    ValidationUtils validationUtils;

    @RequestMapping(method = RequestMethod.GET)
    public List<Contact> getListAllContacts() {
        return contactService.getListAllContacts();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Contact addContact(@RequestBody Contact contact) {
        validationUtils.validateContact(contact);
        if (contactService.isExistContact(contact)) {
            return contactService.updateAddressAddPhone(contact);
        } else {
            return contactService.addContact(contact);
        }
    }

    @RequestMapping(value = "/validate", method = RequestMethod.GET)
    public List<Contact> getListAllContacts2(@RequestParam String string) {
//        return  filterContactListUtils.filter(string,contactService.getListAllContacts());
        return contactService.getListContactsFilteredByString(string);
    }
}

package com.vadimksn.phonebook.utils;

import com.vadimksn.phonebook.data.entity.Contact;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FilterContactListUtils {
    public List<Contact> filter(String str, List<Contact> list) {
        return list.stream().filter(contact -> contact.toStringForFilter().toLowerCase().contains(
                str.toLowerCase())).collect(Collectors.toList());
    }
}

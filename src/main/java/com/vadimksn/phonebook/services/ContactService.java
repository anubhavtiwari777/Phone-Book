package com.vadimksn.phonebook.services;

import java.util.List;

public interface ContactService<EntityModel> {

    EntityModel addContact(EntityModel entityModel);

    EntityModel updateAddressAddPhone(EntityModel entityModel);

    boolean isExistContact(EntityModel entityModel);

    List<EntityModel> getListAllContacts();

    List<EntityModel> getListContactsFilteredByString(String s);

}

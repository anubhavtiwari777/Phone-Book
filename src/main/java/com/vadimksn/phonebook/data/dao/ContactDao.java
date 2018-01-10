package com.vadimksn.phonebook.data.dao;

import java.util.List;

public interface ContactDao<EntityModel> {
    boolean isExistContact(EntityModel entityModel);

    EntityModel addContact(EntityModel entityModel);

    EntityModel updateAddressAddPhone(EntityModel entityModel);

    List<EntityModel> getListAllContacts();

    List<EntityModel> getListContactsFilteredByString(String s);
}

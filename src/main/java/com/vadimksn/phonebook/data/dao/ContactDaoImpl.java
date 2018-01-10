package com.vadimksn.phonebook.data.dao;

import com.vadimksn.phonebook.data.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository("contactDao")
public class ContactDaoImpl implements ContactDao<Contact> {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Contact addContact(Contact contact) {
        jdbcTemplate.update("BEGIN;" +
                        "INSERT INTO phone_book.contacts (name, address) VALUES (?,?);" +
                        "INSERT INTO phone_book.phones (contact_id, phone_number) VALUES (LAST_INSERT_ID(), ?);" +
                        "COMMIT;",
                contact.getName(), contact.getAddress(), contact.getNewPhoneNumber());
        return contact;
    }

    @Override
    public Contact updateAddressAddPhone(Contact contact) {
        jdbcTemplate.update("BEGIN;" +
                        "UPDATE phone_book.contacts SET address=? WHERE id=?;" +
                        "INSERT INTO phone_book.phones (contact_id, phone_number) VALUES (?, ?);" +
                        "COMMIT;",
                contact.getAddress(), contact.getId(), contact.getId(), contact.getNewPhoneNumber());
        return contact;
    }

    @Override
    public boolean isExistContact(Contact contact) {
        boolean exist = false;
        List<Map<String, Object>> list = jdbcTemplate.queryForList(
                "select count(*),id from phone_book.contacts where name=? limit 1;", contact.getName());
        long count = (long) list.get(0).get("count(*)");
        if (count == 1) {
            contact.setId((int) list.get(0).get("id"));
            exist = true;
        }
        return exist;
    }

    @Override
    public List<Contact> getListAllContacts() {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("SELECT c.id,c.name,c.address," +
                "group_concat(distinct phone_number order by phone_number) as phones FROM phone_book.contacts c " +
                "left join phone_book.phones p on c.id=p.contact_id group by c.id;");
        return rowMapper(list);
    }

    @Override
    public List<Contact> getListContactsFilteredByString(String s) {
        List<Map<String, Object>> list = jdbcTemplate.queryForList("\n" +
                "select * from(\n" +
                "               select id from phone_book.contacts WHERE name like '%" + s + "%'or address like '%" + s + "%'\n" +
                "               union distinct\n" +
                "               select  contact_id from phone_book.phones WHERE phone_number like '%" + s + "%'\n" +
                "             ) ss left join(\n" +
                "                             SELECT c.id,c.name,c.address,group_concat(phone_number) as phones FROM phone_book.contacts c left join phone_book.phones p on c.id=p.contact_id group by c.id\n" +
                "                           ) j on j.id=ss.id;\n");
        return rowMapper(list);
    }

    private List<Contact> rowMapper(List<Map<String, Object>> list) {
        List<Contact> contactList = new ArrayList<>();
        for (Map map : list) {
            Contact contact = Contact.builder()
                    .id((Integer) map.get("id"))
                    .name((String) map.get("name"))
                    .address((String) map.get("address")).build();
            String phones = (String) map.get("phones");
            contact.setPhoneNumbers(contact.getId(), phones.split(","));
            contactList.add(contact);
        }
        return contactList;
    }
}

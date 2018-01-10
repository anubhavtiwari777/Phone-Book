package com.vadimksn.phonebook.data.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contact extends EntityModel {
    private String name;
    private String address;
    private String newPhoneNumber;
    private List<PhoneNumber> phoneNumbers;

    @Builder
    public Contact(int id, String name, String address, String newPhoneNumber, List<PhoneNumber> phoneNumbers) {
        super(id);
        this.name = name;
        this.address = address;
        this.newPhoneNumber = newPhoneNumber;
        this.phoneNumbers = phoneNumbers;
    }

    public void setPhoneNumbers(int id, String[] phoneNumbers) {
        List<PhoneNumber> phoneNumberList = new ArrayList<>();
        for (String phone : phoneNumbers) {
            phoneNumberList.add(PhoneNumber.builder().id(id).phoneNumber(phone).build());
        }
        this.phoneNumbers = phoneNumberList;
    }

    public String phoneNumbersToString() {
        final String[] NUMBERS = {""};
        phoneNumbers.forEach(phoneNumber -> NUMBERS[0] += phoneNumber.getPhoneNumber());
        return NUMBERS[0];
    }

    public String toStringForFilter() {
        return name + address + phoneNumbersToString();
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", newPhoneNumber='" + newPhoneNumber + '\'' +
                ", phoneNumbers=" + phoneNumbers +
                '}';
    }
}

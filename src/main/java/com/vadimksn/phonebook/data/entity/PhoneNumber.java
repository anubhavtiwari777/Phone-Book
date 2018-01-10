package com.vadimksn.phonebook.data.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PhoneNumber extends EntityModel {
    private int contactId;
    private String phoneNumber;

    @Builder
    public PhoneNumber(int id, int contactId, String phoneNumber) {
        super(id);
        this.contactId = contactId;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "PhoneNumber{" +
                "contactId=" + contactId +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}

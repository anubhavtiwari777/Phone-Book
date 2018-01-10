package com.vadimksn.phonebook.exception;

public enum ErrorReason {

    INTERNAL_SERVER_ERROR("Internal Server Error"),
    VALIDATION_PARAMETER_IS_NULL("Parameter is null"),
    NAME_IS_LESS_THAN_2("Name length is less than 2"),
    NAME_IS_MORE_THAN_20("Name length is more than 20"),
    NOT_VALID_PHONE_NUMBER("Phone number is not valid"),
    ADDRESS_IS_LESS_THAN_5("Address length is less than 5"),
    ADDRESS_IS_MORE_THAN_30("Address length is more than 30");


    private String description;

    ErrorReason(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
    }

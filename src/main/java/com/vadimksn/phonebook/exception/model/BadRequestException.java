package com.vadimksn.phonebook.exception.model;


import com.vadimksn.phonebook.exception.ErrorReason;
import com.vadimksn.phonebook.exception.model.base.GenericException;

public class BadRequestException extends GenericException {

    public BadRequestException(ErrorReason errorReason, Object... reasonParam){
        super(errorReason,reasonParam);
    }
}
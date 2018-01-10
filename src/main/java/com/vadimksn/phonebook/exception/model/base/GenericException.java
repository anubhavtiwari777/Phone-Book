package com.vadimksn.phonebook.exception.model.base;

import com.vadimksn.phonebook.exception.ErrorReason;
import com.vadimksn.phonebook.utils.ExceptionUtils;
import lombok.Getter;
import lombok.Setter;

public abstract class GenericException extends RuntimeException {

    @Setter
    protected ErrorReason errorReason;

    @Getter    @Setter
    public Object errorDetails;

    public GenericException(ErrorReason errorReason, Object... reasonParams) {
        super((reasonParams == null || (reasonParams.length) == 0) ?
                errorReason.getDescription() : String.format(errorReason.getDescription(), reasonParams));
        this.errorReason = errorReason;
    }

    public ErrorReason getErrorReason() {
        return (errorReason == null ? ErrorReason.INTERNAL_SERVER_ERROR : errorReason);
    }

    public String getFullMessage() {
        return ExceptionUtils.getFullMessage(this);
    }
}

package com.vadimksn.phonebook.advice;


import com.vadimksn.phonebook.exception.model.BadRequestException;
import com.vadimksn.phonebook.exception.model.JsonExceptionInfo;
import com.vadimksn.phonebook.exception.model.base.GenericException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorControllerAdvice {

    @ExceptionHandler({BadRequestException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    JsonExceptionInfo handleBadRequestException(BadRequestException e) {
        return print(e);
    }

    private JsonExceptionInfo print(GenericException e) {
        return new JsonExceptionInfo(e);
    }
}

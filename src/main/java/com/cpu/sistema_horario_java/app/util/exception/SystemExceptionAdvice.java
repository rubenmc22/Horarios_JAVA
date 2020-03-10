package com.cpu.sistema_horario_java.app.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class SystemExceptionAdvice {

    @ResponseBody
    @ExceptionHandler(SystemException.EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public final String handleNotFountExceptions(Exception ex) {
        return ex.getMessage();

    }

    @ResponseBody
    @ExceptionHandler(SystemException.DuplicateEntityException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public final String handleNotFountExceptions1(Exception ex) {
        return ex.getMessage();

    }

    @ResponseBody
    @ExceptionHandler(SystemException.WrongPasswordException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public final String handleWrongPasswordExceptions(Exception ex) {
        return ex.getMessage();

    }
}
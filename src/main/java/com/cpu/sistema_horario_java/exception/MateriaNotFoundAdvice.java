package com.cpu.sistema_horario_java.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class MateriaNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(MateriaNotFounException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String materiaNotFoundHandler(MateriaNotFounException ex) {
        return ex.getMessage();
    }
}
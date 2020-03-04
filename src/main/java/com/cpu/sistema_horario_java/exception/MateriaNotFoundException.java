package com.cpu.sistema_horario_java.exception;

class MateriaNotFounException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    MateriaNotFounException(Long id) {
        super("Materia no hallada: " + id);
    }
}
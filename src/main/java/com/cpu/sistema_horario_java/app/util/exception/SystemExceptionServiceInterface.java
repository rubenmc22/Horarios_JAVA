package com.cpu.sistema_horario_java.app.util.exception;

import com.cpu.sistema_horario_java.app.util.types.EntityType;

public interface SystemExceptionServiceInterface {
    /**
     * Returns a new RuntimeException
     *
     * @param entityType
     * @param exceptionType
     * @param args
     * @return
     */
    static RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return SystemException.throwException(entityType, exceptionType, args);
    }
}

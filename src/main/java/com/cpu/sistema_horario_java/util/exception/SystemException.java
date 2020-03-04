package com.cpu.sistema_horario_java.util.exception;

import com.cpu.sistema_horario_java.util.EntityType;

import org.springframework.stereotype.Component;

@Component
public class SystemException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public static RuntimeException throwException(String messageTemplate, String... args) {
        return new RuntimeException(format(messageTemplate, args));
    }

    public static RuntimeException throwException(EntityType entityType, ExceptionType exceptionType, String... args) {
        String messageTemplate = getMessageTemplate(entityType, exceptionType);
        return throwException(exceptionType, messageTemplate, args);
    }

    public static RuntimeException throwExceptionWithId(EntityType entityType, ExceptionType exceptionType, String id,
            String... args) {
        String messageTemplate = getMessageTemplate(entityType, exceptionType).concat(".").concat(id);
        return throwException(exceptionType, messageTemplate, args);
    }

    public static RuntimeException throwExceptionWithTemplate(EntityType entityType, ExceptionType exceptionType,
            String messageTemplate, String... args) {
        return throwException(exceptionType, messageTemplate, args);
    }

    public static class EntityNotFoundException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public EntityNotFoundException(String message) {
            super(message);
        }
    }

    public static class DuplicateEntityException extends RuntimeException {
        private static final long serialVersionUID = 1L;

        public DuplicateEntityException(String message) {
            super(message);
        }
    }

    private static RuntimeException throwException(ExceptionType exceptionType, String messageTemplate,
            String... args) {
        if (ExceptionType.ENTITY_NOT_FOUND.equals(exceptionType)) {
            return new EntityNotFoundException(format(messageTemplate, args));
        } else if (ExceptionType.DUPLICATE_ENTITY.equals(exceptionType)) {
            return new DuplicateEntityException(format(messageTemplate, args));
        }
        return new RuntimeException(format(messageTemplate, args));
    }

    private static String getMessageTemplate(EntityType entityType, ExceptionType exceptionType) {
        return entityType.name().concat(".").concat(exceptionType.getValue()).toLowerCase();
    }

    private static String format(String template, String... args) {
        return String.format(template, (Object) args);
    }

}
package ru.job4j.cars.exception;

public class UniqueViolationException extends RepositoryException {

    public UniqueViolationException(String message) {
        super(message);
    }

    public UniqueViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
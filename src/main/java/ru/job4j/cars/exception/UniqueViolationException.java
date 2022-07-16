package ru.job4j.cars.exception;

public class UniqueViolationException extends PersistenceException {

    public UniqueViolationException(String message) {
        super(message);
    }

    public UniqueViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
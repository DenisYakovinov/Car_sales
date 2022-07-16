package ru.job4j.cars.exception;

public class EmailReservedException extends ServiceException {

    public EmailReservedException(String message) {
        super(message);
    }

    public EmailReservedException(String message, Throwable cause) {
        super(message, cause);
    }
}
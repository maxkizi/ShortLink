package org.maxkizi.shortlink.authenticationservice.exception;

public class UserNotFoundException extends RuntimeException {
    private static final String message = "Пользователь не найден";

    public UserNotFoundException() {
        super(message);
    }
}

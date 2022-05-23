package org.maxkizi.shortlink.authenticationservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class AuthenticationServiceExceptionHandler {
    private static final String USER_ALREADY_EXISTS_MESSAGE = "Пользователь с таким username уже зарегистрирован";
    private static final String USER_NOT_FOUND_MESSAGE = "Пользователь не зарегистрирован";

    @ExceptionHandler({UserAlreadyExistsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleUserAlreadyExistsException() {
        return USER_ALREADY_EXISTS_MESSAGE;
    }

    @ExceptionHandler({UserNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleUserNotFoundException() {
        return USER_NOT_FOUND_MESSAGE;
    }
}


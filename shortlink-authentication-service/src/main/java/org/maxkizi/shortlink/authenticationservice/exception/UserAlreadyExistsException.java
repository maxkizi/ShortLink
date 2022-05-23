package org.maxkizi.shortlink.authenticationservice.exception;

public class UserAlreadyExistsException extends RuntimeException{
    private static final String message = "Пользователь с таким username уже зарегистрирован";

    public UserAlreadyExistsException(){
        super(message);
    }
}

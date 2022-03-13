package org.maxkizi.shortlink.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class LinkServiceExceptionHandler {
    private static final String NOT_WORKING_LINK_MESSAGE = "Нерабочая ссылка, проверьте ссылку перед использованием";
    private static final String LINK_NOT_FOUND_MESSAGE = "Ссылка (информация по ссылке)  не найдена";
    private static final String EXPIRATION_MESSAGE = "Время жизни ссылки истекло, ссылка будет удалена";

    @ExceptionHandler({NotWorkingLinkException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleNotWorkingLinkException() {
        return NOT_WORKING_LINK_MESSAGE;
    }

    @ExceptionHandler({LinkNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleLinkNotFoundException() {
        return LINK_NOT_FOUND_MESSAGE;
    }

    @ExceptionHandler({ExpireLinkException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleExpireLinkException() {
        return EXPIRATION_MESSAGE;
    }
}

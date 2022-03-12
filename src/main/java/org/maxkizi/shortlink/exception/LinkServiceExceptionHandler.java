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

    @ExceptionHandler({NotWorkingLinkException.class})
    @ResponseStatus(HttpStatus.OK)
    public String handleRestClientException() {
        return NOT_WORKING_LINK_MESSAGE;
    }
}

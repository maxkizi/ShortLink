package org.maxkizi.shortlink.baseservice.exception;

import static org.maxkizi.shortlink.baseservice.exception.Exceptions.NOT_WORKING_LINK_MESSAGE;

public class NotWorkingLinkException extends RuntimeException {
    public NotWorkingLinkException(Throwable throwable) {
        super(NOT_WORKING_LINK_MESSAGE, throwable);
    }

    public NotWorkingLinkException() {
        super(NOT_WORKING_LINK_MESSAGE);
    }
}

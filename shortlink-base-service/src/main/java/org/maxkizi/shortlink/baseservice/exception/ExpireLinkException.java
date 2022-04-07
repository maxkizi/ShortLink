package org.maxkizi.shortlink.baseservice.exception;

import static org.maxkizi.shortlink.baseservice.exception.Exceptions.EXPIRE_LINK_MESSAGE;

public class ExpireLinkException extends RuntimeException {
    public ExpireLinkException() {
        super(EXPIRE_LINK_MESSAGE);
    }
}

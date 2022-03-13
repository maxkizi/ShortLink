package org.maxkizi.shortlink.exception;

import static org.maxkizi.shortlink.exception.Exceptions.EXPIRE_LINK_MESSAGE;

public class ExpireLinkException extends RuntimeException {
    public ExpireLinkException() {
        super(EXPIRE_LINK_MESSAGE);
    }
}

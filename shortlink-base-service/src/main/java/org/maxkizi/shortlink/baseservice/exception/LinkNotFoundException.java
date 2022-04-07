package org.maxkizi.shortlink.baseservice.exception;

import static org.maxkizi.shortlink.baseservice.exception.Exceptions.LINK_NOT_FOUND_MESSAGE;

public class LinkNotFoundException extends RuntimeException {

    public LinkNotFoundException() {
        super(LINK_NOT_FOUND_MESSAGE);
    }
}

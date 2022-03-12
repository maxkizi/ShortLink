package org.maxkizi.shortlink.controller;

public final class Controllers {
    public static final String BASE = "/api/v1";
    public static final String GET_SHORT_LINK = BASE + "/short-link";
    public static final String BY_SHORT_LINK = BASE + "/{shortLink}";

    private Controllers() {
    }
}

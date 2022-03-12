package org.maxkizi.shortlink.service;

public interface ShortLinkService {
    String getFullLink(String shortLink);

    String createShortLink(String fullLink);

    void deleteLink(String shortLink);

}

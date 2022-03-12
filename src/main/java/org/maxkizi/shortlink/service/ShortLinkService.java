package org.maxkizi.shortlink.service;

import org.maxkizi.shortlink.dto.LinkEntityDto;

public interface ShortLinkService {
    String findLinkEntity(String shortLink);

    String createShortLink(String fullLink);

    void incrementCountOfCalls(String shortLink);

    void deleteLink(String shortLink);

    LinkEntityDto getAnalytics(String shortLink);

}

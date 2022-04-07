package org.maxkizi.shortlink.baseservice.service;

import org.maxkizi.shortlink.baseservice.dto.LinkEntityDto;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ShortLinkService {
    void redirect(String shortLink, HttpServletResponse response) throws IOException;

    String createShortLink(String fullLink);

    void deleteLink(String shortLink);

    LinkEntityDto getAnalytics(String shortLink);

}

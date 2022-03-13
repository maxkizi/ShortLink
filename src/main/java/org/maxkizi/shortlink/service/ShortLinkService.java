package org.maxkizi.shortlink.service;

import org.maxkizi.shortlink.dto.LinkEntityDto;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ShortLinkService {
    void redirect(String shortLink, HttpServletResponse response) throws IOException;

    String createShortLink(String fullLink);

    void deleteLink(String shortLink);

    LinkEntityDto getAnalytics(String shortLink);

}

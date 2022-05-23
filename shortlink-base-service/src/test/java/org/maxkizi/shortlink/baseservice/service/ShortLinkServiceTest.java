package org.maxkizi.shortlink.baseservice.service;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.maxkizi.shortlink.baseservice.exception.LinkNotFoundException;
import org.maxkizi.shortlink.baseservice.exception.NotWorkingLinkException;
import org.maxkizi.shortlink.common.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletResponse;

class ShortLinkServiceTest extends BaseIntegrationTest {
    private static final String LINK = "https://github.com/maxkizi/ShortLink/blob/master/pom.xml";
    private static final String BROKEN_LINK = "https://github.com/maxkizi/ShortLink/blo/master/pom.xml";

    private final ShortLinkService service;
    private final LinkRepository repository;

    @Autowired
    public ShortLinkServiceTest(ShortLinkService service, LinkRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @AfterEach
    void deleteAll() {
        repository.deleteAll();
    }

    @Test
    void createAndDeleteShortLinkTest() {
        String shortLink = service.createShortLink(LINK);
        Assertions.assertEquals(String.format("gi.ub-%s", LINK.hashCode()), shortLink);

        service.deleteLink(shortLink);
        Assertions.assertTrue(repository.findById(shortLink).isEmpty());
        Assertions.assertThrows(LinkNotFoundException.class, () -> service.deleteLink(shortLink));
    }

    @Test
    @SneakyThrows
    void redirectAndAnalyticsTest() {
        String shortLink = service.createShortLink(LINK);
        MockHttpServletResponse mockResponse = new MockHttpServletResponse();
        int expectedCallsCount = 0;

        Assertions.assertEquals(expectedCallsCount, service.getAnalytics(shortLink).getCountOfCalls());
        service.redirect(shortLink, mockResponse);

        Assertions.assertEquals(302, mockResponse.getStatus());
        Assertions.assertEquals(++expectedCallsCount, service.getAnalytics(shortLink).getCountOfCalls());
    }

    @Test
    void notWorkingLinkTest_shouldThrows() {
        Assertions.assertThrows(NotWorkingLinkException.class, () -> service.createShortLink(BROKEN_LINK));
    }
}
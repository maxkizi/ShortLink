package org.maxkizi.shortlink.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.maxkizi.shortlink.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

class ShortLinkServiceImplTest extends BaseIntegrationTest {
    private static final String TEST_LINK = "https://www.google.com/webhp?hl=ru&sa=X&ved=0ahUKEwjQ3bjn88D2AhVmkIsKHWQECOIQPAgI";

    private final ShortLinkService service;
    private final LinkRepository repository;

    @Autowired
    public ShortLinkServiceImplTest(ShortLinkService service, LinkRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @AfterEach
    void clearTable() {
        repository.deleteAll();
    }

    @Test
    void shouldCreateShotAndFindFull() {
        String shortLink = service.createShortLink(TEST_LINK);
        Assertions.assertEquals(String.format("%s-%s", "go.le", TEST_LINK.hashCode()), shortLink);
        Assertions.assertEquals(TEST_LINK, service.getFullLink(shortLink));
    }

    @Test
    void test() {
        System.out.println(new Date());
//        Sat Mar 12 20:17:02 MSK 2022
    }
}
package org.maxkizi.shortlink.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.maxkizi.shortlink.exception.NotWorkingLinkException;
import org.maxkizi.shortlink.model.LinkEntity;
import org.maxkizi.shortlink.repository.LinkRepository;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShortLinkServiceImpl implements ShortLinkService {
    private static final String WEB = "web";
    private static final String HTTPS = "https";
    private final LinkRepository repository;


    @Override
    public String getFullLink(String shortLink) {
        return repository.findById(shortLink).orElseThrow(RuntimeException::new).getFullLink();
    }

    @Override
    public String createShortLink(String fullLink) {
        checkHealth(fullLink);
        String shortLink = generateShortLink(fullLink);
        repository.save(
                LinkEntity.builder()
                        .shortLink(shortLink)
                        .fullLink(fullLink)
                        .createdAt(new Date())
                        .build());
        return shortLink;
    }

    @Override
    public void deleteLink(String shortLink) {
        repository.deleteById(shortLink);
    }

    //TODO: доделать для ссылок менее четырёх символов
    private String generateShortLink(String fullLink) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] linkParts = fullLink.split("\\.");
        if (fullLink.contains(WEB)) {
            stringBuilder.append(linkParts[1]);
        } else {
            String httpOrHttpsLink = linkParts[0];
            if (httpOrHttpsLink.contains(HTTPS)) {
                stringBuilder.append(httpOrHttpsLink.substring(8));
            } else {
                stringBuilder.append(httpOrHttpsLink.substring(7));
            }
        }
        return String.format("%s-%s",
                stringBuilder.replace(2, (stringBuilder.toString().length() - 2), "."),
                fullLink.hashCode());
    }

    private void checkHealth(String fullLink) {
        try {
            new RestTemplate().exchange(fullLink, HttpMethod.GET, null, String.class);
        } catch (RestClientException e) {
            log.error("Нерабочая ссылка: {}", fullLink);
            throw new NotWorkingLinkException();
        }
    }
}

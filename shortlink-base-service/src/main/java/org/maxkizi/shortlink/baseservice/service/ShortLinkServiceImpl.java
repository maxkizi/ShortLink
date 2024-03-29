package org.maxkizi.shortlink.baseservice.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.maxkizi.shortlink.baseservice.converter.LinkEntityConverter;
import org.maxkizi.shortlink.baseservice.dto.LinkEntityDto;
import org.maxkizi.shortlink.baseservice.exception.ExpireLinkException;
import org.maxkizi.shortlink.baseservice.exception.LinkNotFoundException;
import org.maxkizi.shortlink.baseservice.exception.NotWorkingLinkException;
import org.maxkizi.shortlink.common.model.LinkEntity;
import org.maxkizi.shortlink.common.repository.LinkRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShortLinkServiceImpl implements ShortLinkService {
    private static final String WWW = "www";
    private static final String HTTPS = "https";
    private final LinkRepository repository;
    private final LinkEntityConverter converter;
    private final RestTemplate restTemplate;

    @Value("${expirationLinkMinutes}")
    private Long expirationMinutes;


    @Override
    public void redirect(String shortLink, HttpServletResponse response) throws IOException {
        LinkEntity linkEntity = repository.findById(shortLink).orElseThrow(LinkNotFoundException::new);
        checkExpiration(linkEntity);
        response.sendRedirect(linkEntity.getFullLink());
        incrementCountOfCalls(linkEntity);
    }


    @Override
    public String createShortLink(String fullLink) {
        String shortLink = generateShortLink(fullLink);
        if (!repository.existsById(shortLink)) {
            checkHealth(fullLink);
            repository.save(LinkEntity.builder()
                    .shortLink(shortLink)
                    .fullLink(fullLink)
                    .createdAt(new Date())
                    .build());
        }
        return shortLink;
    }

    @Override
    public void deleteLink(String shortLink) {
        if (!repository.existsById(shortLink)) {
            throw new LinkNotFoundException();
        }
        repository.deleteById(shortLink);
    }

    @Override
    public LinkEntityDto getAnalytics(String shortLink) {
        return converter.toDto(repository.findById(shortLink).orElseThrow(LinkNotFoundException::new));
    }

    private void incrementCountOfCalls(LinkEntity linkEntity) {
        linkEntity.setCountOfCalls(linkEntity.getCountOfCalls() + 1);
        linkEntity.setCreatedAt(linkEntity.getCreatedAt());
        repository.save(linkEntity);
    }

    //TODO: доделать для ссылок менее четырёх символов
    private String generateShortLink(String fullLink) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] linkParts = fullLink.split("\\.");
        if (fullLink.contains(WWW)) {
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
            restTemplate.exchange(fullLink, HttpMethod.GET, null, String.class);
        } catch (RestClientException e) {
            log.error("Нерабочая ссылка: {}", fullLink);
            throw new NotWorkingLinkException();
        }
    }

    private void checkExpiration(LinkEntity linkEntity) {
        long currentTimeInMinutes = new Date().getTime() / 1000 / 60;
        long createdTimeInMinutes = linkEntity.getCreatedAt().getTime() / 1000 / 60;

        if ((currentTimeInMinutes - createdTimeInMinutes) >= expirationMinutes) {
            repository.deleteById(linkEntity.getShortLink());
            throw new ExpireLinkException();
        }
    }
}

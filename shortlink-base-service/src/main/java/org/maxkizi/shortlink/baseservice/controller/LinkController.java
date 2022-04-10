package org.maxkizi.shortlink.baseservice.controller;

import lombok.RequiredArgsConstructor;
import org.maxkizi.shortlink.baseservice.dto.LinkEntityDto;
import org.maxkizi.shortlink.baseservice.service.ShortLinkService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.maxkizi.shortlink.baseservice.controller.Controllers.DELETE;
import static org.maxkizi.shortlink.baseservice.controller.Controllers.GET_ANALYTICS;
import static org.maxkizi.shortlink.baseservice.controller.Controllers.GET_SHORT_LINK;
import static org.maxkizi.shortlink.baseservice.controller.Controllers.REDIRECT;

@RestController
@RequiredArgsConstructor
public class LinkController {
    private final ShortLinkService service;

    @PostMapping(GET_SHORT_LINK)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_GUEST')")
    public String getShortLink(@RequestBody String fullLink) {
        return service.createShortLink(fullLink);
    }

    @GetMapping(REDIRECT)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_GUEST')")
    public void redirect(HttpServletResponse response, @PathVariable(name = "shortLink") String shortLink) throws IOException {
         service.redirect(shortLink, response);
    }

    @GetMapping(GET_ANALYTICS)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public LinkEntityDto getLinkAnalytics(@PathVariable(name = "shortLink") String shortLink) {
        return service.getAnalytics(shortLink);
    }

    @DeleteMapping(DELETE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(@PathVariable(name = "shortLink") String shortLink) {
        service.deleteLink(shortLink);
    }
}

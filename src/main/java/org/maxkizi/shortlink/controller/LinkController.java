package org.maxkizi.shortlink.controller;

import lombok.RequiredArgsConstructor;
import org.maxkizi.shortlink.service.ShortLinkService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.maxkizi.shortlink.controller.Controllers.GET_SHORT_LINK;
import static org.maxkizi.shortlink.controller.Controllers.BY_SHORT_LINK;

@RestController
@RequiredArgsConstructor
public class LinkController {
    private final ShortLinkService service;

    @PostMapping(GET_SHORT_LINK)
    public String getShortLink(@RequestBody String fullLink) {
        return service.createShortLink(fullLink);
    }

    @GetMapping(BY_SHORT_LINK)
    public void redirect(HttpServletResponse response, @PathVariable(name = "shortLink") String shortLink) throws IOException {
        String fullLink = service.getFullLink(shortLink);
        response.sendRedirect(fullLink);
    }

    @DeleteMapping(BY_SHORT_LINK)
    public void delete(@PathVariable(name = "shortLink") String shortLink) {
        service.deleteLink(shortLink);
    }
}

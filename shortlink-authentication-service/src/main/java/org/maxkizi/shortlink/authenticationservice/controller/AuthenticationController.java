package org.maxkizi.shortlink.authenticationservice.controller;

import lombok.RequiredArgsConstructor;
import org.maxkizi.shortlink.authenticationservice.dto.UserCredentialsDto;
import org.maxkizi.shortlink.authenticationservice.service.AuthenticationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;


    @PostMapping("/api/v1/authenticate")
    public String getJwtToken(@RequestBody UserCredentialsDto userCredentialsDto) {
        return authenticationService.authenticate(userCredentialsDto);
    }

    @PostMapping("/api/v1/signup")
    public String signup(@RequestBody UserCredentialsDto userCredentialsDto) {
        return "success";
    }

}

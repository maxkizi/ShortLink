package org.maxkizi.shortlink.authenticationservice.controller;

import lombok.RequiredArgsConstructor;
import org.maxkizi.shortlink.authenticationservice.dto.ApplicationUserDto;
import org.maxkizi.shortlink.authenticationservice.service.RegistrationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.maxkizi.shortlink.authenticationservice.controller.Controllers.SIGNUP;

@RestController
@RequiredArgsConstructor
public class SignupController {
    private final RegistrationService registrationService;

    @PostMapping(SIGNUP)
    public void signup(@RequestBody ApplicationUserDto applicationUserDto) {
        registrationService.signUp(applicationUserDto);
    }
}

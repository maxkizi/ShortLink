package org.maxkizi.shortlink.authenticationservice.service;

import io.jsonwebtoken.lang.Strings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.maxkizi.shortlink.authenticationservice.dto.UserCredentialsDto;
import org.maxkizi.shortlink.authenticationservice.service.impl.BaseIntegrationTest;
import org.springframework.beans.factory.annotation.Autowired;

class AuthenticationServiceTest extends BaseIntegrationTest {

    private static final String GUEST_USER_NAME = "guest_user";
    private static final String GUEST_USER_PASSWORD = "guest";

    private AuthenticationService authenticationService;

    @Autowired
    public AuthenticationServiceTest(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Test
    public void authenticationTest() {
        String jwtToken = authenticationService.authenticate(new UserCredentialsDto(GUEST_USER_NAME, GUEST_USER_PASSWORD));
        Assertions.assertTrue(Strings.hasText(jwtToken));
    }

    @Test
    public void authenticationTest_shouldThrows() {
        Assertions.assertThrows(Exception.class,
                () -> authenticationService.authenticate(new UserCredentialsDto(GUEST_USER_NAME, "wrongPass")));
    }
}
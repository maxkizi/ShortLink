package org.maxkizi.shortlink.authenticationservice.service;

import io.jsonwebtoken.lang.Strings;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.maxkizi.shortlink.authenticationservice.dto.UserCredentialsDto;
import org.maxkizi.shortlink.common.model.ApplicationUser;
import org.maxkizi.shortlink.common.repository.ApplicationUserRepository;
import org.maxkizi.shortlink.common.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class AuthenticationServiceTest extends BaseIntegrationTest {

    private AuthenticationService authenticationService;
    private ApplicationUserRepository applicationUserRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationServiceTest(AuthenticationService authenticationService, ApplicationUserRepository applicationUserRepository,
                                     RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.authenticationService = authenticationService;
        this.applicationUserRepository = applicationUserRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Test
    public void authenticationTest() {
        ApplicationUser applicationUser = TestDataProvider.buildUser(roleRepository);
        String notEncodedPassword = applicationUser.getPassword();
        applicationUser.setPassword(passwordEncoder.encode(notEncodedPassword));
        applicationUserRepository.save(applicationUser);
        String jwtToken = authenticationService.authenticate(new UserCredentialsDto(applicationUser.getUsername(), notEncodedPassword));
        Assertions.assertTrue(Strings.hasText(jwtToken));
    }

    @Test
    public void authenticationTest_shouldThrows() {
        Assertions.assertThrows(Exception.class,
                () -> authenticationService.authenticate(new UserCredentialsDto("wrongUser", "wrongPass")));
    }

    @AfterEach
    void deleteAll() {
        applicationUserRepository.deleteAll();
    }
}
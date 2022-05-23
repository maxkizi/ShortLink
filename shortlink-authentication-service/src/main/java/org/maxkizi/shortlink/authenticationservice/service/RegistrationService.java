package org.maxkizi.shortlink.authenticationservice.service;

import org.maxkizi.shortlink.authenticationservice.dto.ApplicationUserDto;

public interface RegistrationService {
    void signUp(ApplicationUserDto applicationUserDto);
}

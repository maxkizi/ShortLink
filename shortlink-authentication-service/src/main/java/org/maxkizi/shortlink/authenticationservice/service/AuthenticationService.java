package org.maxkizi.shortlink.authenticationservice.service;

import org.maxkizi.shortlink.authenticationservice.dto.UserCredentialsDto;

public interface AuthenticationService {
    String authenticate(UserCredentialsDto credentials);
}

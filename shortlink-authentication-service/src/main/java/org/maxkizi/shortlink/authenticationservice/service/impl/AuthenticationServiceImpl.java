package org.maxkizi.shortlink.authenticationservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.maxkizi.shortlink.authenticationservice.dto.UserCredentialsDto;
import org.maxkizi.shortlink.authenticationservice.jwt.JwtBuilder;
import org.maxkizi.shortlink.authenticationservice.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtBuilder jwtBuilder;

    @Override
    public String authenticate(UserCredentialsDto credentials) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                credentials.getUsername(), credentials.getPassword()));



        return jwtBuilder.buildJwtToken(userDetailsService.loadUserByUsername(credentials.getUsername()));
    }
}

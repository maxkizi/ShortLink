package org.maxkizi.shortlink.authenticationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserCredentialsDto {
    private final String username;
    private final String password;
}

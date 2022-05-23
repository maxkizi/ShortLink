package org.maxkizi.shortlink.authenticationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Data
public class ApplicationUserDto {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
}

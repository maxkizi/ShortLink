package org.maxkizi.shortlink.authenticationservice.jwt;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalTime;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtBuilder {
    private static final String AUTHORITIES = "authorities";

    @Value("${expirationTokenMinutes}")
    private Long expirationMinutes;
    private final SecretKey secretKey;


    public String buildJwtToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Time.valueOf(LocalTime.now().plusMinutes(expirationMinutes)))
                .claim(AUTHORITIES, userDetails.getAuthorities())
                .signWith(secretKey)
                .compact();
    }
}

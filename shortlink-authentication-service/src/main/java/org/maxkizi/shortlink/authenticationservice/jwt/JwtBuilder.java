package org.maxkizi.shortlink.authenticationservice.jwt;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.util.Date;

@Component
@RequiredArgsConstructor
@ComponentScan(basePackages = "org.maxkizi.shortlink.common")
public class JwtBuilder {
    private static final String AUTHORITIES = "authorities";

    @Value("${expirationTokenMinutes}")
    private Long expirationMinutes;
    private final SecretKey secretKey;


    public String buildJwtToken(UserDetails userDetails) {
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Timestamp.valueOf(LocalDateTime.now().plusMinutes(expirationMinutes)))
                .claim(AUTHORITIES, userDetails.getAuthorities())
                .signWith(secretKey)
                .compact();
    }
}

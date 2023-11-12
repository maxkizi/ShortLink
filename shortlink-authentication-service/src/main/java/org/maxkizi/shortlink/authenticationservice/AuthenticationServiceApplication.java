package org.maxkizi.shortlink.authenticationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"org.maxkizi.shortlink.common.repository", "sdfsd", "sdfsdf"})
@EntityScan(basePackages = {"org.maxkizi.shortlink.common.model"})
@SpringBootApplication(scanBasePackages = {"", "", ""})
public class AuthenticationServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthenticationServiceApplication.class, args);
    }
}

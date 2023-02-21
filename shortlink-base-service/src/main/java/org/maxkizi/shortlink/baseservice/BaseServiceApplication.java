package org.maxkizi.shortlink.baseservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {"org.maxkizi.shortlink.common.repository"})
@EntityScan(basePackages = {"org.maxkizi.shortlink.common.model"})
@ComponentScan(basePackages = "org.maxkizi")
@SpringBootApplication
public class BaseServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseServiceApplication.class, args);
    }
}

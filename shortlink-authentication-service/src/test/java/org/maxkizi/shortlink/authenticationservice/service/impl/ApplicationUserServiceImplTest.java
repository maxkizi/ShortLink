package org.maxkizi.shortlink.authenticationservice.service.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.maxkizi.shortlink.common.model.ApplicationUser;
import org.maxkizi.shortlink.common.model.role.ApplicationUserRole;
import org.maxkizi.shortlink.common.model.role.RolePk;
import org.maxkizi.shortlink.common.model.role.RoleType;
import org.maxkizi.shortlink.common.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

class ApplicationUserServiceImplTest extends BaseIntegrationTest {

    private final ApplicationUserRepository repository;
    private final ApplicationUserServiceImpl service;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationUserServiceImplTest(ApplicationUserRepository repository, ApplicationUserServiceImpl service, BCryptPasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }

    @AfterEach
    void deleteAll() {
        repository.deleteAll();
    }


    @Test
    void createAndDeleteTest() {
        /*System.out.println(passwordEncoder.encode("admin"));
        System.out.println(passwordEncoder.encode("guest"));*/
    }


    private ApplicationUser buildUser() {

        Set<ApplicationUserRole> roles = Set.of(
                new ApplicationUserRole(new RolePk(RoleType.ROLE_ADMIN), "application administrator"),
                new ApplicationUserRole(new RolePk(RoleType.ROLE_GUEST), "application guest")
        );

        return ApplicationUser.builder()
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isEnabled(true)
                .isCredentialsNonExpired(true)
//                .username("username")
                .password(passwordEncoder.encode("password"))
                .roles(roles)
                .build();
    }
}
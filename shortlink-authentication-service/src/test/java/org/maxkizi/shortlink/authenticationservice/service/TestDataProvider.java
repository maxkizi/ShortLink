package org.maxkizi.shortlink.authenticationservice.service;

import org.maxkizi.shortlink.authenticationservice.dto.ApplicationUserDto;
import org.maxkizi.shortlink.common.model.ApplicationUser;
import org.maxkizi.shortlink.common.model.role.ApplicationUserRole;
import org.maxkizi.shortlink.common.repository.RoleRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;


public class TestDataProvider {


    public static ApplicationUser buildUser(RoleRepository roleRepository) {
        Set<ApplicationUserRole> roles = new HashSet<>(roleRepository.findAll());

        return ApplicationUser.builder()
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isEnabled(true)
                .isCredentialsNonExpired(true)
                .firstName("firstName")
                .lastName("lastName")
                .username("username")
                .password("password")
                .roles(roles)
                .build();
    }

    public static ApplicationUserDto buildUserDto() {
        return ApplicationUserDto.builder()
                .firstName("firstName")
                .lastName("lastName")
                .username("username")
                .password("password")
                .build();
    }
}

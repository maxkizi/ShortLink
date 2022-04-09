package org.maxkizi.shortlink.authenticationservice.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.maxkizi.shortlink.authenticationservice.exception.UserNotFoundException;
import org.maxkizi.shortlink.common.model.ApplicationUser;
import org.maxkizi.shortlink.common.model.role.ApplicationUserRole;
import org.maxkizi.shortlink.common.repository.ApplicationUserRepository;
import org.maxkizi.shortlink.common.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

class ApplicationUserServiceImplTest extends BaseIntegrationTest {

    private final ApplicationUserRepository repository;
    private final ApplicationUserServiceImpl service;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public ApplicationUserServiceImplTest(ApplicationUserRepository repository, ApplicationUserServiceImpl service, BCryptPasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.repository = repository;
        this.service = service;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @BeforeEach
    void deleteAll() {
        repository.deleteAll();
    }


    @Test
    void createAndFindAndDeleteUserTest() {
        ApplicationUser userToSave = buildUser();
        service.create(userToSave);
        ApplicationUser foundUser = (ApplicationUser) service.loadUserByUsername(userToSave.getUsername());
        Assertions.assertEquals(userToSave, foundUser);

        service.delete(userToSave.getUsername());
        Assertions.assertThrows(UserNotFoundException.class, () -> service.delete(userToSave.getUsername()));
    }

    @Test
    void updateUser() {
        ApplicationUser userToSave = buildUser();
        service.create(userToSave);
        ApplicationUser foundUser = (ApplicationUser) service.loadUserByUsername(userToSave.getUsername());
        foundUser.setFirstName("anotherFirstName");
        service.update(foundUser);
        ApplicationUser updatedUser = (ApplicationUser) service.loadUserByUsername(foundUser.getUsername());
        Assertions.assertEquals("anotherFirstName", updatedUser.getFirstName());
    }


    private ApplicationUser buildUser() {
        Set<ApplicationUserRole> roles = new HashSet<>(roleRepository.findAll());

        return ApplicationUser.builder()
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isEnabled(true)
                .isCredentialsNonExpired(true)
                .firstName("firstName")
                .lastName("lastName")
                .username("username")
                .password(passwordEncoder.encode("password"))
                .roles(roles)
                .build();
    }
}
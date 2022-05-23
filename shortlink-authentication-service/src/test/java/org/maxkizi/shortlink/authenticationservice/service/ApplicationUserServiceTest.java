package org.maxkizi.shortlink.authenticationservice.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.maxkizi.shortlink.authenticationservice.exception.UserNotFoundException;
import org.maxkizi.shortlink.authenticationservice.service.impl.ApplicationUserServiceImpl;
import org.maxkizi.shortlink.common.model.ApplicationUser;
import org.maxkizi.shortlink.common.repository.ApplicationUserRepository;
import org.maxkizi.shortlink.common.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

class ApplicationUserServiceTest extends BaseIntegrationTest {

    private final ApplicationUserRepository repository;
    private final ApplicationUserServiceImpl service;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public ApplicationUserServiceTest(ApplicationUserRepository repository, ApplicationUserServiceImpl service, BCryptPasswordEncoder passwordEncoder, RoleRepository roleRepository) {
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
        ApplicationUser userToSave = TestDataProvider.buildUser(roleRepository);
        service.create(userToSave);
        ApplicationUser foundUser = (ApplicationUser) service.loadUserByUsername(userToSave.getUsername());
        Assertions.assertEquals(userToSave, foundUser);

        service.delete(userToSave.getUsername());
        Assertions.assertThrows(UserNotFoundException.class, () -> service.delete(userToSave.getUsername()));
    }

    @Test
    void updateUser() {
        ApplicationUser userToSave = TestDataProvider.buildUser(roleRepository);
        service.create(userToSave);
        ApplicationUser foundUser = (ApplicationUser) service.loadUserByUsername(userToSave.getUsername());
        foundUser.setFirstName("anotherFirstName");
        service.update(foundUser);
        ApplicationUser updatedUser = (ApplicationUser) service.loadUserByUsername(foundUser.getUsername());
        Assertions.assertEquals("anotherFirstName", updatedUser.getFirstName());
    }
}
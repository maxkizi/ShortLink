package org.maxkizi.shortlink.authenticationservice.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.maxkizi.shortlink.authenticationservice.dto.ApplicationUserDto;
import org.maxkizi.shortlink.authenticationservice.exception.UserAlreadyExistsException;
import org.maxkizi.shortlink.common.model.ApplicationUser;
import org.maxkizi.shortlink.common.model.role.RolePk;
import org.maxkizi.shortlink.common.model.role.RoleType;
import org.maxkizi.shortlink.common.repository.ApplicationUserRepository;
import org.maxkizi.shortlink.common.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

class RegistrationServiceTest extends BaseIntegrationTest {
    private RegistrationService service;
    private ApplicationUserRepository applicationUserRepository;
    private RoleRepository roleRepository;

    @Autowired
    public RegistrationServiceTest(RegistrationService service,
                                   ApplicationUserRepository applicationUserRepository,
                                   RoleRepository roleRepository) {
        this.service = service;
        this.applicationUserRepository = applicationUserRepository;
        this.roleRepository = roleRepository;
    }

    @Test
    void signUpUserTest() {
        ApplicationUserDto applicationUserDto = TestDataProvider.buildUserDto();
        service.signUp(applicationUserDto);
        ApplicationUser foundUser = applicationUserRepository.findByUsername(applicationUserDto.getUsername());

        Assertions.assertEquals(foundUser.getFirstName(), applicationUserDto.getFirstName());
        Assertions.assertEquals(foundUser.getLastName(), applicationUserDto.getLastName());
        Assertions.assertEquals(foundUser.getUsername(), applicationUserDto.getUsername());

        Assertions.assertTrue(foundUser.isAccountNonExpired());
        Assertions.assertTrue(foundUser.isEnabled());
        Assertions.assertTrue(foundUser.isAccountNonLocked());
        Assertions.assertTrue(foundUser.isCredentialsNonExpired());

        Assertions.assertEquals(foundUser.getRoles().size(), 1);
        Assertions.assertEquals(foundUser.getRoles().stream().findFirst().get(), roleRepository.findById(new RolePk(RoleType.ROLE_GUEST)).get());

        Assertions.assertThrows(UserAlreadyExistsException.class, () -> service.signUp(applicationUserDto));
    }

    @AfterEach
    void deleteAll(){
        applicationUserRepository.deleteAll();
    }
}
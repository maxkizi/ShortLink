package org.maxkizi.shortlink.authenticationservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.maxkizi.shortlink.authenticationservice.converter.ApplicationUserConverter;
import org.maxkizi.shortlink.authenticationservice.dto.ApplicationUserDto;
import org.maxkizi.shortlink.authenticationservice.exception.UserAlreadyExistsException;
import org.maxkizi.shortlink.authenticationservice.service.RegistrationService;
import org.maxkizi.shortlink.common.model.ApplicationUser;
import org.maxkizi.shortlink.common.model.role.ApplicationUserRole;
import org.maxkizi.shortlink.common.model.role.RolePk;
import org.maxkizi.shortlink.common.model.role.RoleType;
import org.maxkizi.shortlink.common.repository.ApplicationUserRepository;
import org.maxkizi.shortlink.common.repository.RoleRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final ApplicationUserRepository applicationUserRepository;
    private final ApplicationUserConverter converter;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public void signUp(ApplicationUserDto applicationUserDto) {
        if (applicationUserRepository.existsById(applicationUserDto.getUsername())) {
            throw new UserAlreadyExistsException();
        }
        ApplicationUser applicationUser = converter.fromDto(applicationUserDto);
        applicationUser.setPassword(passwordEncoder.encode(applicationUser.getPassword()));
        Set<ApplicationUserRole> roles = Set.of(roleRepository.findById(new RolePk(RoleType.ROLE_GUEST)).get());
        applicationUser.setRoles(roles);
        applicationUserRepository.save(applicationUser);
    }
}

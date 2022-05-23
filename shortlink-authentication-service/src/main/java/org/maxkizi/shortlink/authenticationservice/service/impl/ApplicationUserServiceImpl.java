package org.maxkizi.shortlink.authenticationservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.maxkizi.shortlink.authenticationservice.exception.UserNotFoundException;
import org.maxkizi.shortlink.authenticationservice.service.ApplicationUserService;
import org.maxkizi.shortlink.common.model.ApplicationUser;
import org.maxkizi.shortlink.common.repository.ApplicationUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplicationUserServiceImpl implements UserDetailsService, ApplicationUserService {
    private final ApplicationUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        try {
            return repository.findByUsername(username);
        } catch (UsernameNotFoundException e) {
            throw new UserNotFoundException();
        }
    }

    @Override
    public List<ApplicationUser> list() {
        return repository.findAll();
    }

    @Override
    public ApplicationUser create(ApplicationUser user) {
        return repository.save(user);
    }

    @Override
    public ApplicationUser update(ApplicationUser user) {
        return repository.save(user);
    }

    @Override
    public void delete(String username) {
        if (repository.existsById(username)) {
            repository.deleteById(username);
        } else {
            throw new UserNotFoundException();
        }
    }
}

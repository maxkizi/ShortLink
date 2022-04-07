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
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return repository.findByLogin(login);
    }

    @Override
    public ApplicationUser findById(Long id) {
        return repository.findById(id).orElseThrow(UserNotFoundException::new);
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
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new UserNotFoundException();
        }
    }
}

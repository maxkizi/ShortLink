package org.maxkizi.shortlink.authenticationservice.service;

import org.maxkizi.shortlink.common.model.ApplicationUser;

import java.util.List;

public interface ApplicationUserService {
    ApplicationUser findById(Long id);

    List<ApplicationUser> list();

    ApplicationUser create(ApplicationUser user);

    ApplicationUser update(ApplicationUser user);

    void delete(Long id);
}

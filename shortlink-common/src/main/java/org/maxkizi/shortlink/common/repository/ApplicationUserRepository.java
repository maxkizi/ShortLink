package org.maxkizi.shortlink.common.repository;

import org.maxkizi.shortlink.common.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, String> {

    ApplicationUser findByUsername(String username);
}

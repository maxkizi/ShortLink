package org.maxkizi.shortlink.common.repository;

import org.maxkizi.shortlink.common.model.role.ApplicationUserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<ApplicationUserRole, String> {
}

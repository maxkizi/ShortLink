package org.maxkizi.shortlink.common.repository;

import org.maxkizi.shortlink.common.model.role.ApplicationUserRole;
import org.maxkizi.shortlink.common.model.role.RolePk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<ApplicationUserRole, RolePk> {
}

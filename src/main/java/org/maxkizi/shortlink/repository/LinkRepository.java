package org.maxkizi.shortlink.repository;

import org.maxkizi.shortlink.model.LinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<LinkEntity, String> {
}

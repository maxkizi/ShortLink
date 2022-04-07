package org.maxkizi.shortlink.common.repository;


import org.maxkizi.shortlink.common.model.LinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<LinkEntity, String> {
}

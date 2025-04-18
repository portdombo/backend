package com.portdombo.backend.infrastructure.persistence.repository;

import com.portdombo.backend.infrastructure.persistence.entity.TechnologyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TechnologyRepository extends JpaRepository<TechnologyEntity, UUID> {
    boolean existsByName(String name);
}

package com.ezpark.web_service.vehicles.infrastructure.persistence.jpa.repositories;

import com.ezpark.web_service.vehicles.domain.model.entities.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long> {
}

package com.ezpark.web_service.profiles.infrastructure.repositories.jpa;


import com.ezpark.web_service.profiles.domain.model.aggregates.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}

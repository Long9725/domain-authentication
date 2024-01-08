package com.instream.auth.monolithic.domain.application.repository;

import com.instream.auth.monolithic.domain.application.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.UUID;

@EnableJpaRepositories
public interface ApplicationRepository extends JpaRepository<Application, UUID> {
}

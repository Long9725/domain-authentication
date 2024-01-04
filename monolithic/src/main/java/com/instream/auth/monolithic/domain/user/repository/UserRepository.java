package com.instream.auth.monolithic.domain.user.repository;

import com.instream.auth.monolithic.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;
import java.util.UUID;

@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByAccount(String account);
}

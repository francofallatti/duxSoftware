package com.dux.software.repository;

import com.dux.software.model.Auth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Auth, Long> {

    Optional<Auth> findByUsername(String username);
    boolean existsByUsername(String username);
}

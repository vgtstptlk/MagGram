package com.vgtstptlk.gramrestapi.repository;

import com.vgtstptlk.gramrestapi.domains.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    Optional<User> findByLogin(String login);
    Optional<User> findByLoginAndPassword(String login, String password);
}

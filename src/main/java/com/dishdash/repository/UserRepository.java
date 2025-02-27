package com.dishdash.repository;

import com.dishdash.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username, String password);
    Optional<User> findByEmail(String email, String password);
}

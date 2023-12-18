package org.devbox.user.repository;

import org.devbox.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Additional methods if needed
}
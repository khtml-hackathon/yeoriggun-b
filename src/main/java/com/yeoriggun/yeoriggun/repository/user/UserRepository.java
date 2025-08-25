package com.yeoriggun.yeoriggun.repository.user;

import com.yeoriggun.yeoriggun.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

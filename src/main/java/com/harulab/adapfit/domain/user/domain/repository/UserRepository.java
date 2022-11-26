package com.harulab.adapfit.domain.user.domain.repository;

import com.harulab.adapfit.domain.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByAuthId(String authId);
}

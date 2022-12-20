package com.harulab.adapfit.domain.admin.domain.repository;

import com.harulab.adapfit.domain.admin.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByAuthId(String authId);
}

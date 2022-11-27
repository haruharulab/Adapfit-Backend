package com.harulab.adapfit.domain.super_admin.domain.repository;

import com.harulab.adapfit.domain.super_admin.domain.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SuperAdminRepository extends JpaRepository<SuperAdmin, Long> {
    Optional<SuperAdmin> findByAuthId(String authId);
}

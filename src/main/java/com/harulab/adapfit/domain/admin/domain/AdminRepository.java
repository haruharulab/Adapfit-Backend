package com.harulab.adapfit.domain.admin.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByAuthId(String authId);

    @Query("select a from Admin a where a.joinStatus = 'WAITING'")
    List<Admin> findByWaitingList();
}

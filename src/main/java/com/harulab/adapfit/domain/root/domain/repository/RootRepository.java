package com.harulab.adapfit.domain.root.domain.repository;

import com.harulab.adapfit.domain.root.domain.Root;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RootRepository extends JpaRepository<Root, Long> {
    Optional<Root> findByAuthId(String authId);
}

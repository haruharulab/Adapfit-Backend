package com.harulab.adapfit.domain.auth.domain.repository;

import com.harulab.adapfit.domain.auth.domain.AuthId;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthIdRepository extends CrudRepository<AuthId, Long> {

    Optional<AuthId> findByAuthId(String authId);
}

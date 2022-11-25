package com.harulab.adapfit.domain.plan.domain.repository;

import com.harulab.adapfit.domain.plan.domain.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {
}

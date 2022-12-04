package com.harulab.adapfit.domain.recruitment.domain.repository;

import com.harulab.adapfit.domain.recruitment.domain.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface RecruitmentRepository extends JpaRepository<Recruitment, Long>, RecruitmentCustomRepository {
}

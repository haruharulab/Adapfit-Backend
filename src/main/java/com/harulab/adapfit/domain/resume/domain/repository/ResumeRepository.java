package com.harulab.adapfit.domain.resume.domain.repository;

import com.harulab.adapfit.domain.resume.domain.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
}

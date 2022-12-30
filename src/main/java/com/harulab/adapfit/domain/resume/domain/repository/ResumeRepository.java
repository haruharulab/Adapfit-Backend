package com.harulab.adapfit.domain.resume.domain.repository;

import com.harulab.adapfit.domain.resume.domain.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

    @Query("select r from Resume r order by r.createdAt desc")
    List<Resume> findAllByDateDesc();
}

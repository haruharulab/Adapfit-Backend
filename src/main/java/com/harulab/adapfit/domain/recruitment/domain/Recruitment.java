package com.harulab.adapfit.domain.recruitment.domain;

import com.harulab.adapfit.domain.resume.domain.Resume;
import com.harulab.adapfit.domain.recruitment.domain.type.EmploymentPattern;
import com.harulab.adapfit.domain.recruitment.domain.type.Group;
import com.harulab.adapfit.domain.recruitment.presentation.dto.req.RecruitmentUpdateRequestDto;
import com.harulab.adapfit.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Recruitment extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(length = 36)
    private String title;

    @NotNull
    @Column(columnDefinition = "LONGTEXT")
    private String content;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Group jobGroup;

    private Integer career;

    @NotNull
    @Enumerated(EnumType.STRING)
    private EmploymentPattern employmentPattern;

    @NotNull
    private String workingArea;

    @OneToMany(mappedBy = "recruitment")
    private List<Resume> resumes;

    @Builder
    public Recruitment(String title, String content, Group group, Integer career, EmploymentPattern employmentPattern, String workingArea) {
        this.title = title;
        this.content = content;
        this.jobGroup = group;
        this.career = career;
        this.employmentPattern = employmentPattern;
        this.workingArea = workingArea;
    }

    public void updateInfo(RecruitmentUpdateRequestDto req) {
        this.title = req.getTitle();
        this.content = req.getContent();
        this.jobGroup = Group.valueOf(req.getGroup());
        this.career = req.getCareer();
        this.employmentPattern = EmploymentPattern.valueOf(req.getEmploymentPattern());
        this.workingArea = req.getWorkingArea();
    }

    public void addApply(Resume resume) {
        this.resumes.add(resume);
    }
}

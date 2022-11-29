package com.harulab.adapfit.domain.recruitment.domain;

import com.harulab.adapfit.domain.recruitment.domain.type.EmploymentPattern;
import com.harulab.adapfit.domain.recruitment.domain.type.Group;
import com.harulab.adapfit.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Recruitment extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 제목
    @NotNull
    @Column(length = 36)
    private String title;

    // 내용
    @NotNull
    @Column(columnDefinition = "LONGTEXT")
    private String content;

    // 직군
    @NotNull
    @Enumerated(EnumType.STRING)
    private Group jobGroup;

    // 경력
    private Integer career;

    // 고용형태(정규직, 인턴)
    @NotNull
    @Enumerated(EnumType.STRING)
    private EmploymentPattern employmentPattern;

    // 근무지역
    @NotNull
    private String workingArea;

    @Builder
    public Recruitment(String title, String content, Group group, Integer career, EmploymentPattern employmentPattern, String workingArea) {
        this.title = title;
        this.content = content;
        this.jobGroup = group;
        this.career = career;
        this.employmentPattern = employmentPattern;
        this.workingArea = workingArea;
    }
}

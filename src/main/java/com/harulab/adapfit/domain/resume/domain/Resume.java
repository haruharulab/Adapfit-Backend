package com.harulab.adapfit.domain.resume.domain;

import com.harulab.adapfit.domain.resume.presentation.dto.req.ResumeUpdateRequestDto;
import com.harulab.adapfit.domain.recruitment.domain.Recruitment;
import com.harulab.adapfit.infrastructure.s3.S3FileResponseDto;
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
public class Resume {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String phoneNumber;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String fileUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruitment_id")
    private Recruitment recruitment;

    @Builder
    public Resume(String name, String email, String phoneNumber, String fileName, String fileUrl) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.fileName = fileName;
        this.fileUrl = fileUrl;
    }

    public void confirmRecruitment(Recruitment recruitment) {
        this.recruitment = recruitment;
        recruitment.addApply(this);
    }

    public void updateInfo(ResumeUpdateRequestDto req, S3FileResponseDto res) {
        this.name = req.getName();
        this.email = req.getEmail();
        this.phoneNumber = req.getPhoneNumber();
        this.fileName = res.getFileName();
        this.fileUrl = res.getFileUrl();
    }
}

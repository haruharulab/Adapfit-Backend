package com.harulab.adapfit.domain.resume.domain;

import com.harulab.adapfit.domain.recruitment.domain.Recruitment;
import com.harulab.adapfit.domain.log.global.entity.BaseTimeEntity;
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
public class Resume extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String phoneNumber;

    @Column(nullable = false)
    private String resume;

    private String portfolio;

    private String etcFile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruitment_id")
    private Recruitment recruitment;

    @Column
    private boolean saw;

    @Builder
    public Resume(
            String name, String email, String phoneNumber, String resume,
            String portfolio, String etcFile, Recruitment recruitment, boolean saw
    ) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.resume = resume;
        this.portfolio = portfolio;
        this.etcFile = etcFile;
        this.recruitment = recruitment;
        this.saw = saw;
    }

    public void confirmRecruitment(Recruitment recruitment) {
        this.recruitment = recruitment;
        recruitment.addApply(this);
    }

    public void updateSaw() {
        this.saw = true;
    }

}

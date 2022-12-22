package com.harulab.adapfit.domain.plan.domain;

import com.harulab.adapfit.domain.admin.domain.Admin;
import com.harulab.adapfit.domain.category.domain.Category;
import com.harulab.adapfit.domain.plan.exception.DontAccessOtherPlanException;
import com.harulab.adapfit.domain.plan.presentation.dto.req.PlanUpdateRequestDto;
import com.harulab.adapfit.global.entity.BaseTimeEntity;
import com.harulab.adapfit.infrastructure.s3.S3FileResponseDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Plan extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 36)
    @NotNull
    private String title;

    @Column(length = 128)
    @NotNull
    private String subTitle;

    @Lob
    @NotNull
    private String content;

    @Column(nullable = false)
    private String thumbnailName;

    @Column(nullable = false)
    private String thumbnailUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @Builder
    public Plan(String title, String subTitle, String content, String thumbnailName, String thumbnailUrl) {
        this.title = title;
        this.subTitle = subTitle;
        this.content = content;
        this.thumbnailName = thumbnailName;
        this.thumbnailUrl = thumbnailUrl;
    }

    public void confirmCategory(Category category) {
        this.category = category;
        category.addPlan(this);
    }

    public void isRightWriter(Admin writer) {
        if (!Objects.equals(writer.getId(), writer.getId())) {
            throw DontAccessOtherPlanException.EXCEPTION;
        }
    }

    public void updateThumbnail(S3FileResponseDto res) {
        this.thumbnailName = res.getFileUrl();
        this.thumbnailUrl = res.getFileUrl();
    }

    public void updateInfo(PlanUpdateRequestDto req, Category category) {
        this.title = req.getTitle();
        this.content = req.getContent();
        this.category = category;
    }
}

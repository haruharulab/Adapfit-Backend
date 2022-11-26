package com.harulab.adapfit.domain.plan.domain;

import com.harulab.adapfit.domain.plan.presentation.dto.req.PlanUpdateRequestDto;
import com.harulab.adapfit.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Plan {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 36)
    @NotNull
    private String title;

    @Column(columnDefinition = "LONGTEXT")
    @NotNull
    private String content;

    @Column(length = 256)
    private String imgUrl;

    @Column(length = 256)
    private String imgPath;

    // 추후 카테고리 추가 (@OneToMany);

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    private User writer;

    @Builder
    public Plan(String title, String content, String imgUrl, String imgPath) {
        this.title = title;
        this.content = content;
        this.imgUrl = imgUrl;
        this.imgPath = imgPath;
    }

    public void saveWriter(User user) {
        this.writer = user;
    }

    public void updatePlanInfo(PlanUpdateRequestDto req) {
        this.title = req.getTitle();
        this.content = req.getContent();
    }
}

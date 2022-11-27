package com.harulab.adapfit.domain.plan.domain;

import com.harulab.adapfit.domain.category.domain.Category;
import com.harulab.adapfit.domain.plan.exception.DontAccessOtherPlanException;
import com.harulab.adapfit.domain.plan.presentation.dto.req.PlanUpdateRequestDto;
import com.harulab.adapfit.domain.user.domain.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@DynamicUpdate
@DynamicInsert
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
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
    @OneToMany(mappedBy = "plan", cascade = CascadeType.REMOVE)
    private final List<Category> categories = new ArrayList<>();

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

    // 연관관계 편의 메서드
    public void confirmWriter(User user) {
        this.writer = user;
        user.addPlan(this);
    }

    public void addCategory(Category category) {
        this.categories.add(category);
    }

    // Plan Info Update
    public void updatePlanInfo(PlanUpdateRequestDto req) {
        this.title = req.getTitle();
        this.content = req.getContent();
    }

    public void isRightWriter(User writer) {
        if (!Objects.equals(writer.getId(), writer.getId())) {
            throw DontAccessOtherPlanException.EXCEPTION;
        }
    }

}

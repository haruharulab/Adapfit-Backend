package com.harulab.adapfit.domain.image.domain;

import com.harulab.adapfit.domain.plan.domain.Plan;
import com.harulab.adapfit.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2022. 12. 16.
 */

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Image extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String imageName;

    @Column(nullable = false)
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @Builder
    public Image(String fileName, String fileUrl) {
        this.imageName = fileName;
        this.imageUrl = fileUrl;
    }

    public void confirmPlan(Plan plan) {
        this.plan = plan;
    }

    public void updateImageInfo(String fileName, String fileUrl) {
        this.imageName = fileName;
        this.imageUrl = fileUrl;
    }
}

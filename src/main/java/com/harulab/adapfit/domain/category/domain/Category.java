package com.harulab.adapfit.domain.category.domain;

import com.harulab.adapfit.domain.plan.domain.Plan;
import com.harulab.adapfit.global.entity.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
public class Category extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.REMOVE)
    private List<Plan> plans;

    public Category(String name) {
        this.name = name;
    }

    public void addPlan(Plan plan) {
        this.plans.add(plan);
    }

    public void updateName(String categoryName) {
        this.name = categoryName;
    }
}

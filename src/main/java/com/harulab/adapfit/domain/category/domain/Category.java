package com.harulab.adapfit.domain.category.domain;

import com.harulab.adapfit.domain.plan.domain.Plan;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
@DynamicInsert
@DynamicUpdate
@Entity
public class Category {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    private Plan plan;

    public Category(String name) {
        this.name = name;
    }

    public void confirmPlan(Plan plan) {
        this.plan = plan;
        plan.addCategory(this);
    }
}

package com.harulab.adapfit.domain.admin.presentation.dto.res;

import com.harulab.adapfit.domain.plan.domain.Plan;
import com.harulab.adapfit.domain.plan.presentation.dto.res.PlanResponseDto;
import com.harulab.adapfit.domain.admin.domain.Admin;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class AdminResponseDto {

    private final Long userId;
    private final String authId;
    private final String email;
    private final String phoneNumber;
    private final String nickname;
    private final String authority;
    private final List<PlanResponseDto> plans;

    public AdminResponseDto(Admin admin, List<Plan> plans) {
        this.userId = admin.getId();
        this.authId = admin.getAuthId();
        this.email = admin.getEmail();
        this.phoneNumber = admin.getPhoneNumber();
        this.nickname = admin.getNickname();
        this.authority = admin.getAuthority().name();
        this.plans = plans.stream()
                .map(plan -> new PlanResponseDto(plan, plan.getCategory()))
                .collect(Collectors.toList());
    }
}

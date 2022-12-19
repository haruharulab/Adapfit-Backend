package com.harulab.adapfit.domain.user.presentation.dto.res;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.harulab.adapfit.domain.plan.domain.Plan;
import com.harulab.adapfit.domain.plan.presentation.dto.res.PlanResponseDto;
import com.harulab.adapfit.domain.user.domain.User;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UserResponseDto {

    private final Long userId;
    private final String email;
    private final String phoneNumber;
    private final String nickname;
    private final String authority;
    private final List<PlanResponseDto> plans;

    public UserResponseDto(User user, List<Plan> plans) {
        this.userId = user.getId();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.nickname = user.getNickname();
        this.authority = user.getAuthority().name();
        this.plans = plans.stream()
                .map(plan -> new PlanResponseDto(plan, plan.getCategory()))
                .collect(Collectors.toList());
    }
}

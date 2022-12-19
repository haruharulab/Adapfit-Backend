package com.harulab.adapfit.domain.root.presentation.dto.res;

import com.harulab.adapfit.domain.root.domain.SuperAdmin;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2022. 12. 19.
 */

@Getter
public class SuperAdminResponseDto {

    private final Long superAdminId;
    private final LocalDateTime createdAt;
    private final String authority;
    private final String email;
    private final String nickname;

    public SuperAdminResponseDto(SuperAdmin superAdmin) {
        this.superAdminId = superAdmin.getId();
        this.createdAt = superAdmin.getCreatedAt();
        this.authority = superAdmin.getAuthority().name();
        this.email = superAdmin.getEmail();
        this.nickname = superAdmin.getNickname();
    }
}

package com.harulab.adapfit.domain.root.presentation.dto.res;

import com.harulab.adapfit.domain.root.domain.Root;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2022. 12. 19.
 */

@Getter
public class RootResponseDto {

    private final Long superAdminId;
    private final LocalDateTime createdAt;
    private final String authority;
    private final String email;
    private final String nickname;

    public RootResponseDto(Root root) {
        this.superAdminId = root.getId();
        this.createdAt = root.getCreatedAt();
        this.authority = root.getAuthority().name();
        this.email = root.getEmail();
        this.nickname = root.getNickname();
    }
}

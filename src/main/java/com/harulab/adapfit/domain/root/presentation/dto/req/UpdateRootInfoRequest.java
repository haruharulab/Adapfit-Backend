package com.harulab.adapfit.domain.root.presentation.dto.req;

import lombok.Getter;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2023. 01. 06.
 */

@Getter
public class UpdateRootInfoRequest {

    private final String authId;
    private final String email;
    private final String nickname;

    public UpdateRootInfoRequest(String authId, String email, String nickname) {
        this.authId = authId;
        this.email = email;
        this.nickname = nickname;
    }

}

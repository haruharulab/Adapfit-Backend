package com.harulab.adapfit.domain.auth.presentation;

import com.harulab.adapfit.domain.auth.presentation.dto.req.LoginRequestDto;
import com.harulab.adapfit.domain.auth.service.UserLoginService;
import com.harulab.adapfit.domain.auth.service.UserTokenRefreshService;
import com.harulab.adapfit.global.security.jwt.dto.TokenResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth") // 모든 권한이 사용하는 엔드포인트
public class AuthController {

    private final UserLoginService loginService;
    private final UserTokenRefreshService userTokenRefreshService;

    @PostMapping("/token") // USER, ADMIN
    public TokenResponseDto login(@RequestBody @Valid LoginRequestDto req) {
        return loginService.execute(req);
    }

    @PutMapping("/refresh") // USER, ADMIN, SUPER_ADMIN
    public TokenResponseDto tokenRefresh(@RequestHeader("X-Refresh-Token") String refreshToken) {
        return userTokenRefreshService.execute(refreshToken);
    }
}

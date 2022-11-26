package com.harulab.adapfit.domain.auth.presentation;

import com.harulab.adapfit.domain.auth.presentation.dto.req.LoginRequestDto;
import com.harulab.adapfit.domain.auth.service.SuperAdminLoginService;
import com.harulab.adapfit.domain.auth.service.LogoutService;
import com.harulab.adapfit.global.security.jwt.dto.TokenResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/super/auth")
public class SuperAdminAuthController {

    private final SuperAdminLoginService superAdminLoginService;
    private final LogoutService logoutService;

    @PostMapping("/token")
    public TokenResponseDto login(@RequestBody @Valid LoginRequestDto req) {
        return superAdminLoginService.execute(req);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void logout(@RequestHeader("Authorization") String accessToken) {
        logoutService.execute(accessToken);
    }
}

package com.harulab.adapfit.domain.auth.presentation;

import com.harulab.adapfit.domain.auth.presentation.dto.req.LoginRequestDto;
import com.harulab.adapfit.domain.auth.service.AdminLoginService;
import com.harulab.adapfit.global.security.jwt.dto.TokenResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/admin")
public class AdminLoginController {

    private final AdminLoginService adminLoginService;

    @PostMapping("/token")
    public TokenResponseDto login(@RequestBody @Valid LoginRequestDto req) {
        return adminLoginService.execute(req);
    }

}

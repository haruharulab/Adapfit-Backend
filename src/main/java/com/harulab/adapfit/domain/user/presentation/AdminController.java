package com.harulab.adapfit.domain.user.presentation;

import com.harulab.adapfit.domain.super_admin.presentation.dto.req.UpdateAccountInfoRequestDto;
import com.harulab.adapfit.domain.super_admin.service.AdminService;
import com.harulab.adapfit.domain.user.service.LogoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RequiredArgsConstructor
@RequestMapping("/admin")
@RestController
public class AdminController {

    private final AdminService adminService;
    private final LogoutService logoutService;

    @PutMapping // TODO FIX
    public void updateAdminInfo(@RequestBody @Valid UpdateAccountInfoRequestDto req) {
        adminService.updateAccountInfo(req);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void logout(@RequestHeader("Authorization") String accessToken) {
        logoutService.execute(accessToken);
    }
}

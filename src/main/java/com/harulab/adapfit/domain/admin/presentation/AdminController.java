package com.harulab.adapfit.domain.admin.presentation;

import com.harulab.adapfit.domain.root.presentation.dto.req.UpdateAccountInfoRequestDto;
import com.harulab.adapfit.domain.admin.presentation.dto.req.PasswordRequestDto;
import com.harulab.adapfit.domain.admin.presentation.dto.req.AdminRequestDto;
import com.harulab.adapfit.domain.admin.presentation.dto.res.AdminResponseDto;
import com.harulab.adapfit.domain.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class AdminController {

    private final AdminService adminService;

    @GetMapping
    public AdminResponseDto myInfo() {
        return adminService.getMyInfo();
    }

    @PostMapping
    public void join(@RequestBody @Valid AdminRequestDto req) {
        adminService.join(req);
    }

    @PutMapping
    public void update(@RequestBody @Valid UpdateAccountInfoRequestDto req) {
        adminService.updateAccountInfo(req);
    }

    @PutMapping("/pw")
    public void updatePw(@RequestBody @Valid PasswordRequestDto req) {
        adminService.updatePassword(req);
    }

}

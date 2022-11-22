package com.harulab.adapfit.domain.admin.presentation;

import com.harulab.adapfit.domain.admin.presentation.dto.req.JoinAdminRequestDto;
import com.harulab.adapfit.domain.admin.presentation.dto.req.UpdateAccountInfoRequestDto;
import com.harulab.adapfit.domain.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RequiredArgsConstructor
@RequestMapping("/admin")
@RestController
public class AdminController {

    private final AdminService adminService;

    @PostMapping // 비밀번호 일치 여부 검증해야함
    public void join(@RequestBody @Valid JoinAdminRequestDto req) {
        adminService.join(req);
    }

    @PutMapping
    public void updateAdminInfo(@RequestBody @Valid UpdateAccountInfoRequestDto req) {
        adminService.updateAccountInfo(req);
    }
}

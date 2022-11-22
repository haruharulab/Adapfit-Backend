package com.harulab.adapfit.domain.admin.presentation;

import com.harulab.adapfit.domain.admin.presentation.dto.req.UpdateAccountInfoRequestDto;
import com.harulab.adapfit.domain.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RequiredArgsConstructor
@RequestMapping("/admin")
@RestController
public class AdminController {

    private final AdminService adminService;

    @PutMapping
    public void updateAdminInfo(@RequestBody @Valid UpdateAccountInfoRequestDto req) {
        adminService.updateAccountInfo(req);
    }
}

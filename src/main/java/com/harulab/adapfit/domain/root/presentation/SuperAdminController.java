package com.harulab.adapfit.domain.root.presentation;

import com.harulab.adapfit.domain.admin.presentation.dto.req.PasswordRequestDto;
import com.harulab.adapfit.domain.admin.service.AdminService;
import com.harulab.adapfit.domain.root.presentation.dto.req.SuperAdminCreateRequestDto;
import com.harulab.adapfit.domain.root.presentation.dto.req.UpdateAccountInfoRequestDto;
import com.harulab.adapfit.domain.root.presentation.dto.res.SuperAdminResponseDto;
import com.harulab.adapfit.domain.root.service.SuperAdminService;
import com.harulab.adapfit.domain.admin.presentation.dto.res.AdminCreateResponseDto;
import com.harulab.adapfit.domain.admin.presentation.dto.res.AdminResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RequiredArgsConstructor
@RequestMapping("/super")
@RestController
public class SuperAdminController {

    private final SuperAdminService superAdminService;
    private final AdminService adminService;

    @GetMapping
    public SuperAdminResponseDto getCurrent() {
        return superAdminService.getMine();
    }

    @GetMapping("/{adminId}")
    public AdminResponseDto getAdminDetail(@PathVariable Long adminId) {
        return adminService.getDetail(adminId);
    }

    @PostMapping
    public void createRoot(@RequestBody @Valid SuperAdminCreateRequestDto req) {
        superAdminService.createRoot(req);
    }

    @PostMapping("/admin")
    public AdminCreateResponseDto createAdmin() {
        return superAdminService.createAdmin();
    }

    @GetMapping("/all")
    public List<AdminResponseDto> searchUserList() {
        return superAdminService.getUserList();
    }

    @PutMapping("/{adminId}")
    public void changeRole(
            @PathVariable Long adminId,
            @RequestBody @Valid UpdateAccountInfoRequestDto req
    ) {
        superAdminService.updateAdminInfo(adminId, req);
    }

    @PutMapping("/pw/{adminId}")
    public void changePw(
            @PathVariable Long adminId,
            @RequestBody PasswordRequestDto req
            ) {
        adminService.updatePassword(adminId, req);
    }

    @DeleteMapping("/{adminId}")
    public void deleteAdmin(@PathVariable Long adminId) {
        adminService.remove(adminId);
    }

}
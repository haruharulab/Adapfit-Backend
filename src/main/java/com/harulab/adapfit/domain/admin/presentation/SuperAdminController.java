package com.harulab.adapfit.domain.admin.presentation;

import com.harulab.adapfit.domain.admin.service.AdminService;
import com.harulab.adapfit.domain.user.presentation.dto.res.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RequiredArgsConstructor
@RequestMapping("/super")
@RestController
public class SuperAdminController {

    private final AdminService adminService;

    @GetMapping
    public List<UserResponseDto> searchUserList() {
        return adminService.getUserList();
    }

    @PutMapping("/{id}")
    public void changeRole(@PathVariable Long id) {
        adminService.updateAuthorityAdmin(id);
    }


}

package com.harulab.adapfit.domain.root.presentation;

import com.harulab.adapfit.domain.root.presentation.dto.req.SuperAdminCreateRequestDto;
import com.harulab.adapfit.domain.root.presentation.dto.req.UpdateAccountInfoRequestDto;
import com.harulab.adapfit.domain.root.presentation.dto.res.SuperAdminResponseDto;
import com.harulab.adapfit.domain.root.service.SuperAdminService;
import com.harulab.adapfit.domain.user.presentation.dto.res.AdminCreateResponseDto;
import com.harulab.adapfit.domain.user.presentation.dto.res.UserResponseDto;
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

    @GetMapping
    public SuperAdminResponseDto getDetail() {
        return superAdminService.getInfo();
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
    public List<UserResponseDto> searchUserList() {
        return superAdminService.getUserList();
    }

    @PutMapping("/{adminId}")
    public void changeRole(
            @PathVariable Long adminId,
            @RequestBody @Valid UpdateAccountInfoRequestDto req
    ) {
        superAdminService.updateAdminInfo(adminId, req);
    }

}
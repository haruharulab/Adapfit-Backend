package com.harulab.adapfit.domain.root.presentation;

import com.harulab.adapfit.domain.root.presentation.dto.req.SuperAdminCreateRequestDto;
import com.harulab.adapfit.domain.root.service.SuperAdminService;
import com.harulab.adapfit.domain.user.presentation.dto.req.UserRequestDto;
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

    @PostMapping
    public void createRoot(@RequestBody @Valid SuperAdminCreateRequestDto req) {
        superAdminService.createRoot(req);
    }

    @PostMapping("/admin")
    public AdminCreateResponseDto createAdmin() {
        return superAdminService.createAdmin();
    }

    @GetMapping
    public List<UserResponseDto> searchUserList() {
        return superAdminService.getUserList();
    }

    @PutMapping("/{id}")
    public void changeRole(@PathVariable Long id) {
        superAdminService.updateAuthorityAdmin(id);
    }

}
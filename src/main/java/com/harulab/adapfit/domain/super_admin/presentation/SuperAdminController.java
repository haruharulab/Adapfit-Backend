package com.harulab.adapfit.domain.super_admin.presentation;

import com.harulab.adapfit.domain.super_admin.presentation.dto.req.SuperAdminCreateRequestDto;
import com.harulab.adapfit.domain.super_admin.service.LogoutService;
import com.harulab.adapfit.domain.super_admin.service.SuperAdminService;
import com.harulab.adapfit.domain.user.presentation.dto.res.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    private final LogoutService logoutService;

    @PostMapping
    public void createRoot(@RequestBody @Valid SuperAdminCreateRequestDto req) {
        superAdminService.createRoot(req);
    }

    @GetMapping
    public List<UserResponseDto> searchUserList() {
        return superAdminService.getUserList();
    }

    @PutMapping("/{id}")
    public void changeRole(@PathVariable Long id) {
        superAdminService.updateAuthorityAdmin(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void logout(@RequestHeader("Authorization") String accessToken) {
        logoutService.execute(accessToken);
    }
}

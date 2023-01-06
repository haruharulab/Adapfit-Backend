package com.harulab.adapfit.domain.root.presentation;

import com.harulab.adapfit.domain.admin.presentation.dto.req.PasswordRequestDto;
import com.harulab.adapfit.domain.admin.service.AdminService;
import com.harulab.adapfit.domain.root.presentation.dto.req.AdminCreateRequestDto;
import com.harulab.adapfit.domain.root.presentation.dto.req.RootCreateRequestDto;
import com.harulab.adapfit.domain.root.presentation.dto.req.UpdateAccountInfoRequestDto;
import com.harulab.adapfit.domain.root.presentation.dto.req.UpdateRootInfoRequest;
import com.harulab.adapfit.domain.root.presentation.dto.res.RootResponseDto;
import com.harulab.adapfit.domain.root.service.RootService;
import com.harulab.adapfit.domain.admin.presentation.dto.res.AdminResponseDto;
import com.harulab.adapfit.global.generic.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RequiredArgsConstructor
@RequestMapping("/super")
@RestController
public class RootController {

    private final RootService rootService;
    private final AdminService adminService;

    @GetMapping
    public RootResponseDto getCurrent() {
        return rootService.getMine();
    }

    @GetMapping("/{adminId}")
    public AdminResponseDto getAdminDetail(@PathVariable Long adminId) {
        return adminService.getDetail(adminId);
    }

    @PostMapping
    public void createRoot(@RequestBody @Valid RootCreateRequestDto req) {
        rootService.createRoot(req);
    }

    @PostMapping("/admin")
    public AdminResponseDto createAdmin(@RequestBody @Valid AdminCreateRequestDto req) {
        return rootService.createAdmin(req);
    }

    @GetMapping("/all")
    public ResultResponse<List<AdminResponseDto>> searchUserList() {
        List<AdminResponseDto> users = rootService.getUserList();
        return new ResultResponse<>(users.size(), users);
    }

    @PutMapping
    public void updateInfo(@RequestBody UpdateRootInfoRequest req) {
        rootService.updateMyInfo(req);
    }

    @PutMapping("/{adminId}")
    public void changeRole(
            @PathVariable Long adminId,
            @RequestBody @Valid UpdateAccountInfoRequestDto req
    ) {
        rootService.updateAdminInfo(adminId, req);
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
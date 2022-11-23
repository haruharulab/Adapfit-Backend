package com.harulab.adapfit.domain.admin.presentation;

import com.harulab.adapfit.domain.admin.presentation.dto.res.AdminResponseDto;
import com.harulab.adapfit.domain.admin.service.AdminService;
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
    public List<AdminResponseDto> searchByJoinWaitingList() {
        return adminService.getJoinWaitingList();
    }

    @PutMapping("/{id}/{res}")
    public void acceptJoin(@PathVariable Long id, @PathVariable boolean res) {
        adminService.updateJoinStatus(id, res);
    }


}

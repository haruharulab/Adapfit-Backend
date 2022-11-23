package com.harulab.adapfit.domain.admin.presentation;

import com.harulab.adapfit.domain.admin.presentation.dto.res.AdminResponseDto;
import com.harulab.adapfit.domain.admin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}

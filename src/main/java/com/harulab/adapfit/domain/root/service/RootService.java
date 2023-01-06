package com.harulab.adapfit.domain.root.service;

import com.harulab.adapfit.domain.admin.domain.Admin;
import com.harulab.adapfit.domain.root.domain.Root;
import com.harulab.adapfit.domain.root.domain.repository.RootRepository;
import com.harulab.adapfit.domain.root.facade.RootFacade;
import com.harulab.adapfit.domain.root.presentation.dto.req.AdminCreateRequestDto;
import com.harulab.adapfit.domain.root.presentation.dto.req.RootCreateRequestDto;
import com.harulab.adapfit.domain.root.presentation.dto.req.UpdateAccountInfoRequestDto;
import com.harulab.adapfit.domain.root.presentation.dto.req.UpdateRootInfoRequest;
import com.harulab.adapfit.domain.root.presentation.dto.res.RootResponseDto;
import com.harulab.adapfit.domain.admin.facade.AdminFacade;
import com.harulab.adapfit.domain.admin.presentation.dto.res.AdminResponseDto;
import com.harulab.adapfit.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class RootService {

    private final RootRepository rootRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdminFacade adminFacade;
    private final RootFacade rootFacade;

    @Transactional
    public AdminResponseDto createAdmin(AdminCreateRequestDto req) {
        Admin admin = adminFacade.create(req.toEntity());
        admin.encodePassword(passwordEncoder);
        return new AdminResponseDto(admin);
    }

    public RootResponseDto getMine() {
        return new RootResponseDto(rootFacade.getCurrentRoot());
    }

    @Transactional
    public void createRoot(RootCreateRequestDto req) {
        rootRepository.save(req.toEntity()).encodePassword(passwordEncoder);
    }

    public List<AdminResponseDto> getUserList() {
        return adminFacade.findAll()
                .stream()
                .map(AdminResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateAdminInfo(Long adminId, UpdateAccountInfoRequestDto req) {
        adminFacade.findById(adminId).updateInfo(req);
    }

    @Transactional
    public void updateMyInfo(UpdateRootInfoRequest req) {
        Root root = rootFacade.getCurrentRoot();
        root.update(req);
    }

}

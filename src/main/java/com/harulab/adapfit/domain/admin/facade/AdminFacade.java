package com.harulab.adapfit.domain.admin.facade;

import com.harulab.adapfit.domain.admin.domain.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AdminFacade {

    private final AdminRepository adminRepository;


}

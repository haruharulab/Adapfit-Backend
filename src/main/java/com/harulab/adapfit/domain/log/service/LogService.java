package com.harulab.adapfit.domain.log.service;

import com.harulab.adapfit.domain.admin.facade.AdminFacade;
import com.harulab.adapfit.domain.log.facade.LogFacade;
import com.harulab.adapfit.domain.log.presentation.dto.req.LogCreateRequestDto;
import com.harulab.adapfit.domain.root.facade.RootFacade;
import com.harulab.adapfit.global.annotation.ServiceWithTransactionalReadOnly;
import com.harulab.adapfit.global.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 최원용
 * @version 2.0.0
 * @Created by 최원용 on 2022. 12. 29.
 */

@RequiredArgsConstructor
@ServiceWithTransactionalReadOnly
public class LogService {

    private final LogFacade logFacade;
    private final AdminFacade adminFacade;
    private final RootFacade rootFacade;
    private final JwtUtil jwtUtil;

    @Transactional
    public void save(LogCreateRequestDto req, String token) {
        String authority = jwtUtil.extractAuthorityFromToken(token);
        if (authority.equals("ADMIN")) authorityIsAdmin(req);
        if (authority.equals("ROOT")) authorityIsRoot(req);
    }

    private void authorityIsAdmin(LogCreateRequestDto req) {
        logFacade.save(req.toEntity(adminFacade.getCurrentAdmin().getNickname()));
    }

    private void authorityIsRoot(LogCreateRequestDto req) {
        logFacade.save(req.toEntity(rootFacade.getCurrentRoot().getNickname()));
    }
}

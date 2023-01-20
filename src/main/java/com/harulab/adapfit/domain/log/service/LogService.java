package com.harulab.adapfit.domain.log.service;

import com.harulab.adapfit.domain.admin.facade.AdminFacade;
import com.harulab.adapfit.domain.log.domain.Log;
import com.harulab.adapfit.domain.log.facade.LogFacade;
import com.harulab.adapfit.domain.log.presentation.dto.res.LogResponse;
import com.harulab.adapfit.domain.root.facade.RootFacade;
import com.harulab.adapfit.domain.log.global.annotation.ServiceWithTransactionalReadOnly;
import com.harulab.adapfit.domain.log.global.exception.NotFoundAuthIdException;
import com.harulab.adapfit.domain.log.global.utils.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional(propagation = Propagation.NESTED, rollbackFor = NotFoundAuthIdException.class)
    public void save(String message, String token) {
        String authority = jwtUtil.extractAuthorityFromToken(token);
        if (authority.equals("ADMIN")) authorityIsAdmin(message);
        if (authority.equals("ROOT")) authorityIsRoot(message);
    }

    private void authorityIsAdmin(String message) {
        logFacade.save(Log.builder()
                .nickname(adminFacade.getCurrentAdmin().getNickname())
                .didAt(LocalDateTime.now())
                .message(message)
                .build());
    }

    private void authorityIsRoot(String message) {
        logFacade.save(Log.builder()
                .nickname(rootFacade.getCurrentRoot().getNickname())
                .didAt(LocalDateTime.now())
                .message(message)
                .build());
    }

//    @Cacheable(cacheNames = "searchAll", key = "#root.target + #root.methodName", sync = true, cacheManager = "rcm")
    public List<LogResponse> searchAll() {
        return logFacade.findAllOrderByDateAtDesc()
                .stream()
                .map(LogResponse::new)
                .collect(Collectors.toList());
    }

//    @CacheEvict(cacheNames = "searchAll", allEntries = true, beforeInvocation = true, cacheManager = "rcm")
    @Transactional
    public void removeAll() {
        logFacade.removeAll();
    }
}

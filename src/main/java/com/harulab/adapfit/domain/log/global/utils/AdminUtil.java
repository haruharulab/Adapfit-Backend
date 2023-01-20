package com.harulab.adapfit.domain.log.global.utils;

import com.harulab.adapfit.domain.log.global.security.auth.user.AuthDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public class AdminUtil {
    public static AuthDetails getCurrentUser() {
        return (AuthDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }
}


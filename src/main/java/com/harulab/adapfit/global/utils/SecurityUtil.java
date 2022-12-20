package com.harulab.adapfit.global.utils;

import com.harulab.adapfit.global.security.auth.user.AuthDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static AuthDetails getCurrentUser() {
        return (AuthDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }
}


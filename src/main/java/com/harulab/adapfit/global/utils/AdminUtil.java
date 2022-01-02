package com.harulab.adapfit.global.utils;

import com.harulab.adapfit.global.security.auth.admin.AuthDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public class AdminUtil {
    public static AuthDetails getCurrentUser() {
        return (AuthDetails) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();
    }
}


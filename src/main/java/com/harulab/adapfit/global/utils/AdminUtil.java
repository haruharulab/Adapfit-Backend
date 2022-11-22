package com.harulab.adapfit.global.utils;

import com.harulab.adapfit.global.security.auth.admin.AdminDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public class AdminUtil {
    public static AdminDetails getCurrentUser() {
        return (AdminDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}

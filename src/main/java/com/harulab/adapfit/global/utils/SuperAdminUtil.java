package com.harulab.adapfit.global.utils;

import com.harulab.adapfit.global.security.auth.super_admin.SuperAdminDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public class SuperAdminUtil {
    public static SuperAdminDetails getCurrentUser() {
        return (SuperAdminDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}

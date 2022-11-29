package com.harulab.adapfit.global.utils;

import com.harulab.adapfit.global.security.auth.root.SuperAdminDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public class SuperAdminUtil {
    public static SuperAdminDetails getCurrentUser() {
        return (SuperAdminDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}

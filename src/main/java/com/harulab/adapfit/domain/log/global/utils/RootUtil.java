package com.harulab.adapfit.domain.log.global.utils;

import com.harulab.adapfit.domain.log.global.security.auth.root.RootDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public class RootUtil {
    public static RootDetails getCurrentUser() {
        return (RootDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}

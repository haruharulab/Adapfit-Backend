package com.harulab.adapfit.global.utils;

import com.harulab.adapfit.global.security.auth.root.RootDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public class RootUtil {
    public static RootDetails getCurrentUser() {
        return (RootDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}

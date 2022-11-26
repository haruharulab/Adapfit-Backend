package com.harulab.adapfit.global.security.auth.admin;

import com.harulab.adapfit.domain.super_admin.domain.SuperAdmin;
import com.harulab.adapfit.domain.super_admin.domain.SuperAdminRepository;
import com.harulab.adapfit.domain.user.domain.type.Authority;
import com.harulab.adapfit.global.exception.AdminNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdminDetailsService implements UserDetailsService {

    private final SuperAdminRepository superAdminRepository;

    @Override
    public UserDetails loadUserByUsername(String authId) throws UsernameNotFoundException {
        SuperAdmin superAdmin = superAdminRepository.findByAuthId(authId)
                .orElseThrow(() -> AdminNotFoundException.EXCEPTION);
        return new AdminDetails(superAdmin.getAuthId(), Authority.SUPER_ADMIN);
    }

//    org.springframework.web.util.NestedServletException:
//    Request processing failed; nested exception is
//    java.lang.ClassCastException:
//    class com.harulab.adapfit.global.security.auth.admin.AdminDetails
//    cannot be cast to class
//    com.harulab.adapfit.global.security.auth.user.AuthDetails
//    (com.harulab.adapfit.global.security.auth.admin.AdminDetails
//    and com.harulab.adapfit.global.security.auth.user.AuthDetails
//    are in unnamed module of loader 'app')

}

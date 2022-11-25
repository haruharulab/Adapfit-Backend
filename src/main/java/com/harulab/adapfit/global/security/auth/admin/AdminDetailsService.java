package com.harulab.adapfit.global.security.auth.admin;

import com.harulab.adapfit.domain.admin.domain.Admin;
import com.harulab.adapfit.domain.admin.domain.AdminRepository;
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

    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String authId) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByAuthId(authId)
                .orElseThrow(() -> AdminNotFoundException.EXCEPTION);
        return new AdminDetails(admin.getAuthId(), Authority.SUPER_ADMIN);
    }

}

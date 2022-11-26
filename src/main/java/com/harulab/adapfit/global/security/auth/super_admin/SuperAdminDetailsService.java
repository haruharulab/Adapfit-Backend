package com.harulab.adapfit.global.security.auth.super_admin;

import com.harulab.adapfit.domain.super_admin.domain.SuperAdminRepository;
import com.harulab.adapfit.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SuperAdminDetailsService implements UserDetailsService {

    private final SuperAdminRepository superAdminRepository;

    @Override
    public UserDetails loadUserByUsername(String authId) throws UsernameNotFoundException {
        return superAdminRepository.findByAuthId(authId)
                .map(SuperAdminDetails::new)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

}

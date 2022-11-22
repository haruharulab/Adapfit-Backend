package com.harulab.adapfit.global.security.auth.admin;

import com.harulab.adapfit.domain.admin.domain.AdminRepository;
import com.harulab.adapfit.global.exception.AuthIdNotFoundException;
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
        return adminRepository.findByAuthId(authId)
                .map(AdminDetails::new)
                .orElseThrow(() -> AuthIdNotFoundException.EXCEPTION);
    }
}

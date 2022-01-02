package com.harulab.adapfit.global.security.auth.admin;

import com.harulab.adapfit.domain.admin.domain.repository.AdminRepository;
import com.harulab.adapfit.domain.admin.exception.AdminNotFoundException;
import com.harulab.adapfit.global.annotation.ServiceWithTransactionalReadOnly;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@ServiceWithTransactionalReadOnly
@RequiredArgsConstructor
public class AuthDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String authId) throws UsernameNotFoundException {
        return adminRepository.findByAuthId(authId)
                .map(AuthDetails::new)
                .orElseThrow(() -> AdminNotFoundException.EXCEPTION);
    }
}

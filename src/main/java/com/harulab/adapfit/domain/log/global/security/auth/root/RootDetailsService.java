package com.harulab.adapfit.domain.log.global.security.auth.root;

import com.harulab.adapfit.domain.log.global.annotation.ServiceWithTransactionalReadOnly;
import com.harulab.adapfit.domain.root.domain.repository.RootRepository;
import com.harulab.adapfit.domain.admin.exception.AdminNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@ServiceWithTransactionalReadOnly
@RequiredArgsConstructor
public class RootDetailsService implements UserDetailsService {

    private final RootRepository rootRepository;

    @Override
    public UserDetails loadUserByUsername(String authId) throws UsernameNotFoundException {
        return rootRepository.findByAuthId(authId)
                .map(RootDetails::new)
                .orElseThrow(() -> AdminNotFoundException.EXCEPTION);
    }

}

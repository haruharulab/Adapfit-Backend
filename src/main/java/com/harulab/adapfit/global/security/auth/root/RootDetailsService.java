package com.harulab.adapfit.global.security.auth.root;

import com.harulab.adapfit.global.annotation.ServiceWithTransactionalReadOnly;
import com.harulab.adapfit.domain.root.domain.repository.RootRepository;
import com.harulab.adapfit.domain.admin.exception.AdminNotFoundException;
import com.harulab.adapfit.global.exception.RootNotFoundException;
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
                .orElseThrow(() -> RootNotFoundException.EXCEPTION);
    }

}

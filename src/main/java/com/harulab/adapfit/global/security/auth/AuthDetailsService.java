package com.harulab.adapfit.global.security.auth;

import com.harulab.adapfit.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class AuthDetailsService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String authId) throws UsernameNotFoundException {
        return userRepository.findByAuthId(authId)
                .map(AuthDetails::new)
                .orElseThrow(() -> new IllegalAccessError("유저 아이디를 찾을 수 없습니다."));
    }
}

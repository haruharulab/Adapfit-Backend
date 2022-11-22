package com.harulab.adapfit.domain.user.facade;

import com.harulab.adapfit.domain.user.domain.User;
import com.harulab.adapfit.domain.user.domain.UserRepository;
import com.harulab.adapfit.global.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User getCurrentUser() {
        return SecurityUtil.getCurrentUser().getUser();
    }
}

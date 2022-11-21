package com.harulab.adapfit.domain.user.facade;

import com.harulab.adapfit.domain.user.domain.User;
import com.harulab.adapfit.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {

    private final UserRepository userRepository;

    public void save(User user) {
        userRepository.save(user);
    }
}

package com.harulab.adapfit.domain.user.facade;

import com.harulab.adapfit.domain.user.domain.User;
import com.harulab.adapfit.domain.user.domain.repository.UserRepository;
import com.harulab.adapfit.domain.user.exception.UserNotFoundException;
import com.harulab.adapfit.global.utils.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }

    public User findByAuthId(String authId) {
        return userRepository.findByAuthId(authId)
                .orElseThrow(() -> UserNotFoundException.EXCEPTION);
    }
}

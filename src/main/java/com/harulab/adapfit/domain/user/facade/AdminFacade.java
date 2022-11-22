package com.harulab.adapfit.domain.user.facade;

import com.harulab.adapfit.domain.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminFacade {

    private final UserRepository userRepository;

}

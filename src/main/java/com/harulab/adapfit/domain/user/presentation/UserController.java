package com.harulab.adapfit.domain.user.presentation;

import com.harulab.adapfit.domain.user.presentation.dto.req.UserRequestDto;
import com.harulab.adapfit.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping
    public void join(@RequestBody @Valid UserRequestDto req) {
        userService.join(req);
    }
}

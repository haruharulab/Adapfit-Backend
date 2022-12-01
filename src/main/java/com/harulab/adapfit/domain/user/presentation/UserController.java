package com.harulab.adapfit.domain.user.presentation;

import com.harulab.adapfit.domain.root.presentation.dto.req.UpdateAccountInfoRequestDto;
import com.harulab.adapfit.domain.user.presentation.dto.req.PasswordRequestDto;
import com.harulab.adapfit.domain.user.presentation.dto.req.UserRequestDto;
import com.harulab.adapfit.domain.user.presentation.dto.res.UserResponseDto;
import com.harulab.adapfit.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping
    public UserResponseDto myInfo() {
        return userService.getMyInfo();
    }

    @PostMapping
    public void join(@RequestBody @Valid UserRequestDto req) {
        userService.join(req);
    }

    @PutMapping
    public void update(@RequestBody @Valid UpdateAccountInfoRequestDto req) {
        userService.updateAccountInfo(req);
    }

    @PutMapping("/pw")
    public void updatePw(@RequestBody @Valid PasswordRequestDto req) {
        userService.updatePassword(req);
    }

}

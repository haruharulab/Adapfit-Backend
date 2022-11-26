package com.harulab.adapfit.domain.user.presentation;

import com.harulab.adapfit.domain.super_admin.presentation.dto.req.UpdateAccountInfoRequestDto;
import com.harulab.adapfit.domain.user.service.LogoutService;
import com.harulab.adapfit.domain.user.presentation.dto.req.UserRequestDto;
import com.harulab.adapfit.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;
    private final LogoutService logoutService;

    @PostMapping
    public void join(@RequestBody @Valid UserRequestDto req) {
        userService.join(req);
    }

    @PutMapping // TODO FIX
    public void updateUserInfo(@RequestBody @Valid UpdateAccountInfoRequestDto req) {
        userService.updateAccountInfo(req);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void logout(@RequestHeader("Authorization") String accessToken) {
        logoutService.execute(accessToken);
    }
}

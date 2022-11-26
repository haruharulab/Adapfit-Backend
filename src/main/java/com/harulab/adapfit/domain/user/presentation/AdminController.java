package com.harulab.adapfit.domain.user.presentation;

import com.harulab.adapfit.domain.user.service.LogoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequiredArgsConstructor
@RequestMapping("/admin")
@RestController
public class AdminController {

    private final LogoutService logoutService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping
    public void logout(@RequestHeader("Authorization") String accessToken) {
        logoutService.execute(accessToken);
    }
}

package com.yeoriggun.yeoriggun.controller.user;

import com.yeoriggun.yeoriggun.dto.login.LoginRequestDto;
import com.yeoriggun.yeoriggun.dto.login.LoginResponseDto;
import com.yeoriggun.yeoriggun.service.user.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class LoginController {

    private final LoginService loginService;

    public LoginController(final LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto request) {
        return loginService.login(request);
    }
}

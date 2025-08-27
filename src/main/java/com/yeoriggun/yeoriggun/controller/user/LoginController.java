package com.yeoriggun.yeoriggun.controller.user;

import com.yeoriggun.yeoriggun.dto.login.LoginRequestDto;
import com.yeoriggun.yeoriggun.dto.login.LoginResponseDto;
import com.yeoriggun.yeoriggun.service.user.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@Tag(name = "로그인", description = "로그인 관련 API")
public class LoginController {

    private final LoginService loginService;

    public LoginController(final LoginService loginService) {
        this.loginService = loginService;
    }

    @Operation(summary = "로그인", description = "로그인합니다.")
    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto request) {
        return loginService.login(request);
    }
}

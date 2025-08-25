package com.yeoriggun.yeoriggun.dto.login;

import com.yeoriggun.yeoriggun.entity.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LoginResponseDto {
    private Long userId;
    private String name;
    private String role;
}

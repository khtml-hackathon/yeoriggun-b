package com.yeoriggun.yeoriggun.service.user;

import com.yeoriggun.yeoriggun.dto.login.LoginRequestDto;
import com.yeoriggun.yeoriggun.dto.login.LoginResponseDto;
import com.yeoriggun.yeoriggun.entity.user.User;
import com.yeoriggun.yeoriggun.repository.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UserRepository userRepository;

    public LoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoginResponseDto login(LoginRequestDto request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("사용자가 없습니다."));

        // 비밀번호 단순 비교 (해커톤용)
        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        return LoginResponseDto.builder()
                .userId((long) user.getUserId())
                .name(user.getName())
                .role(String.valueOf(user.getRole()))
                .build();
    }
}

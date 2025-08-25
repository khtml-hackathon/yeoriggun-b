package com.yeoriggun.yeoriggun.service.user;

import com.yeoriggun.yeoriggun.dto.user.UserDto;
import com.yeoriggun.yeoriggun.entity.user.User;
import com.yeoriggun.yeoriggun.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDto getUserById(Long userId){
        Optional<User> user = userRepository.findById(userId);

        return user.map(UserDto::fromEntity)
                .orElseThrow(() -> new RuntimeException("해당 아이디의 유저 없음"));
    }
}

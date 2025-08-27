package com.yeoriggun.yeoriggun.controller.user;

import com.yeoriggun.yeoriggun.dto.user.UserDto;
import com.yeoriggun.yeoriggun.service.user.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "유저 정보", description = "유저 정보 관련 API")
public class UserController {

    private final UserService userService;

    @Operation(summary = "유저 정보", description = "유저의 정보를 가져옵니다.")
    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }

}

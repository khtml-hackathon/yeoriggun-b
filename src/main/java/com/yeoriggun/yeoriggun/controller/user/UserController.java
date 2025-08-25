package com.yeoriggun.yeoriggun.controller.user;

import com.yeoriggun.yeoriggun.dto.user.UserDto;
import com.yeoriggun.yeoriggun.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public UserDto getUser(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }

}

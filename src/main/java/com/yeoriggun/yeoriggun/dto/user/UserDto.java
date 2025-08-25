package com.yeoriggun.yeoriggun.dto.user;

import com.yeoriggun.yeoriggun.entity.user.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long userId;
    private String email;
    private String name;
    private String phone;
    private String role;
    private String businessNumber;

    public static UserDto fromEntity(User user) {
        return UserDto.builder()
                .userId((long) user.getUserId())
                .email(user.getEmail())
                .name(user.getName())
                .phone(user.getPhone())
                .role(user.getRole().name())
                .businessNumber(user.getBusinessNumber())
                .build();
    }
}
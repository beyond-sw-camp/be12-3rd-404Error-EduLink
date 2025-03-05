package com.example.package404.manager.model.dto;

import com.example.package404.user.model.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ManagerRequestDto {
    private String email;
    private String password;
    private String nickname;

    public User toEntity(String encodedPassword) {
        return User.builder()
                .email(email)
                .password(encodedPassword)
                .build();
    }
}

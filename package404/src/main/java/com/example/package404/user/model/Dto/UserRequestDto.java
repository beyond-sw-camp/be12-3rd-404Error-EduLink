package com.example.package404.user.model.Dto;


import com.example.package404.user.model.User;
import lombok.*;

import java.time.LocalDate;

public class UserRequestDto {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Builder
    public static class SignupRequest {
        private String email;
        private String password;
        private String name;
        private LocalDate birth;
        private String role;

        public User toEntity(String encodedPassword, String role) {
            return User.builder()
                    .email(email)
                    .password(encodedPassword)
                    .name(name)
                    .birth(birth)
                    .role(role)
                    .build();
        }


    }
}

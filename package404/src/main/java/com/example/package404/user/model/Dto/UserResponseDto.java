package com.example.package404.user.model.Dto;

import com.example.package404.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserResponseDto {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class SignupResponse {
        private Long idx;
        private String email;

        public static SignupResponse from(User entity) {
            return new SignupResponse(entity.getIdx(), entity.getEmail());
        }
    }
}

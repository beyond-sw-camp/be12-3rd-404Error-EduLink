package com.example.package404.user.model.Dto;

import com.example.package404.user.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
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

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class BasicUserResponseDto {
        @Schema(description = "유저 고유 번호")
        private Long idx;
        @Schema(description = "유저 이메일")
        private String email;
        @Schema(description = "유저 이름")
        private String name;
        @Schema(description = "유저 역할")
        private String role;

        public static BasicUserResponseDto from(User entity) {
            return BasicUserResponseDto.builder()
                    .idx(entity.getIdx())
                    .email(entity.getEmail())
                    .name(entity.getName())
                    .role(entity.getRole())
                    .build();
        }
    }
}

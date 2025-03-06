package com.example.package404.manager.model.dto;

import com.example.package404.user.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ManagerResponseDto {
    @Schema(description = "매니저 고유 번호")
    private Long idx;
    @Schema(description = "매니저 이메일")
    private String email;

    public static ManagerResponseDto of(User entity) {
        return ManagerResponseDto.builder()
                .idx(entity.getIdx())
                .email(entity.getEmail())
                .build();
    }
}

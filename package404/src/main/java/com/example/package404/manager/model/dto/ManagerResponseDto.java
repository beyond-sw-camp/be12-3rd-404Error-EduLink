package com.example.package404.manager.model.dto;

import com.example.package404.user.model.User;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ManagerResponseDto {
    private Long idx;
    private String email;

    public static ManagerResponseDto of(User entity) {
        return ManagerResponseDto.builder()
                .idx(entity.getIdx())
                .email(entity.getEmail())
                .build();
    }
}

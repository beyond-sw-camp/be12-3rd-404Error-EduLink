package com.example.package404.user.model.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserUpdateRequestDto {
    @Schema(description = "새로운 생일", example = "2000-01-01")
    private LocalDate birth;

    @Schema(description = "새로운 비밀번호", example = "newPassword123")
    private String password;

    @Schema(description = "학생의 경우 새로운 주소", example = "서울시 강남구")
    private String address;

    @Schema(description = "강사의 경우 새로운 포트폴리오", example = "Updated portfolio content")
    private String portfolio;
}

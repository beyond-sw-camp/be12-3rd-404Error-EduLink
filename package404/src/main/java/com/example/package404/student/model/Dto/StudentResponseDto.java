package com.example.package404.student.model.Dto;

import com.example.package404.student.model.StudentDetail;
import com.example.package404.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentResponseDto {
    private Long idx;
    private String email;
    private String password;
    private String name;
    private String role;

    StudentDetailResponseDto studentDetail;

    public static StudentResponseDto from(StudentDetail s) {
        User user = s.getUser();
        return StudentResponseDto.builder()
                .idx(user.getIdx())
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .role(user.getRole())
                .studentDetail(StudentDetailResponseDto.from(user.getStudentDetail()))
                .build();
    }
}

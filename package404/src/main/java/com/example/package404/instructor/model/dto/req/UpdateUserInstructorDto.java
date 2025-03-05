package com.example.package404.instructor.model.dto.req;

import com.example.package404.instructor.model.Instructor;
import com.example.package404.user.model.User;
import lombok.Getter;

import static com.example.package404.user.model.User.*;

@Getter
public class UpdateUserInstructorDto {
    private String email;
    private String password;
    private String name;
    private String record;
    private String portfolio;

    // Instructor 정보 변환 (빌더 패턴 적용)
    public User toInstructorEntity(Long userIdx) {
        // User 객체를 빌더 패턴으로 생성
        User user = User.builder()
                .idx(userIdx)
                .email(email)
                .password(password)
                .name(name)
                .build();

        // Instructor 객체를 빌더 패턴으로 생성
        Instructor instructor = Instructor.builder()
                .record(record)
                .portfolio(portfolio)
                .user(user)  // User와 연결
                .build();


        return user;  // 업데이트된 User 객체를 반환
    }
}

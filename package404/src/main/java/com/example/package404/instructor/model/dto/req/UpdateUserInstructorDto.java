package com.example.package404.instructor.model.dto.req;

import com.example.package404.instructor.model.Instructor;
import com.example.package404.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UpdateUserInstructorDto {
    private String password;
    private String record;
    private String portfolio;

    public User toInstructorEntity(Long userIdx) {
        User user = User.builder()
                .idx(userIdx)
                .password(password)
                .build();

        Instructor instructor = Instructor.builder()
                .record(record)
                .portfolio(portfolio)
                .user(user)  // User와 연결
                .build();


        user.assignInstructor(instructor);

        return user;  // 업데이트된 User 객체를 반환
    }
}

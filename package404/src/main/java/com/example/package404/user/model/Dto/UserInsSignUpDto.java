package com.example.package404.user.model.Dto;

import com.example.package404.instructor.model.Instructor;
import com.example.package404.user.model.User;
import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UserInsSignUpDto {

    private String email;
    private String password;
    private String name;
    private LocalDate birth;
    private String role;

    public User toEntity(String encodedPassword, String role) {
            User user = User.builder()
                    .email(email)
                    .password(encodedPassword)
                    .name(name)
                    .birth(birth)
                    .role(role)
                    .build();

            Instructor instructor = Instructor.builder()
                    .user(user)
                    .record("기수")
                    .portfolio("강사 포트폴리오")
                    .build();
        user.assignInstructor(instructor);
        return user;
    }
}

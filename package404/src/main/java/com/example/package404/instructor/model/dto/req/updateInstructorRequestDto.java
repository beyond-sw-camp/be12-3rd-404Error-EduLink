package com.example.package404.instructor.model.dto.req;

import com.example.package404.instructor.model.Instructor;
import com.example.package404.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class updateInstructorRequestDto {



    private String email;
    private String password;
    private String name;
    private LocalDate birth;
    private String record;
    private String portfolio;


    public Instructor toEntity(User user) {

        return Instructor.builder()
                .record(record)
                .portfolio(portfolio)
                .build();
    }


}

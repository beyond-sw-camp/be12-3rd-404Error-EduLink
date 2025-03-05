package com.example.package404.instructor.model.dto.req;

import com.example.package404.instructor.model.Course;
import com.example.package404.instructor.model.Curriculum;
import com.example.package404.instructor.model.Homework;
import com.example.package404.instructor.model.Instructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class HomeworkReqDto {

    private String title;
    private String content;



    public Homework toEntity(Course course) {
        return Homework.builder()
                .title(title)
                .content(content)
                .course(course)
                .build();
    }

}

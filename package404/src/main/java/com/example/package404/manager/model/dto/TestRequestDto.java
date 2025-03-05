package com.example.package404.manager.model.dto;

import com.example.package404.instructor.model.Course;
import com.example.package404.manager.model.Test;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TestRequestDto {
    private String title;
    private String content;
    private Course course;

    public Test toEntity(){
        return Test.builder()
                .title(title)
                .content(content)
                .course(course)
                .build();
    }
}

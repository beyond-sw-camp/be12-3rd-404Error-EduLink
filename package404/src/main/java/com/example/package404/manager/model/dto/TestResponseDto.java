package com.example.package404.manager.model.dto;

import com.example.package404.instructor.model.Course;
import com.example.package404.manager.model.Test;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TestResponseDto {
    private Long idx;
    private String title;
    private String content;
    private Course course;

    public static TestResponseDto of(Test entity) {
        return TestResponseDto.builder()
                .idx(entity.getIdx())
                .title(entity.getTitle())
                .content(entity.getContent())
                .course(entity.getCourse())
                .build();
    }
}

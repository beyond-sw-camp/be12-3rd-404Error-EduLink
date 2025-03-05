package com.example.package404.instructor.model.dto.req;

import com.example.package404.instructor.model.Course;
import com.example.package404.instructor.model.Curriculum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurriculumRegisterDto {


    private LocalDate curriculumDate; //일자
    private int curriculumDay; //일수
    private String curriculumSubject; // 교과목
    private String content; // 내용
    private int curriculumHours; // 편성시간



    public Curriculum toEntity(Course course) {
        return Curriculum.builder()
                .curriculumDate(curriculumDate)
                .curriculumDay(curriculumDay)
                .curriculumSubject(curriculumSubject)
                .curriculumHours(curriculumHours)
                .content(content)
                .course(course)
                .build();
    }
}

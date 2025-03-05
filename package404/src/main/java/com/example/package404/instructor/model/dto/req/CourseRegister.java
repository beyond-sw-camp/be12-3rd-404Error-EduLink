package com.example.package404.instructor.model.dto.req;

import com.example.package404.instructor.model.Course;
import com.example.package404.instructor.model.Instructor;
import com.example.package404.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourseRegister {

    private String name;
    private int generation;


    List<CurriculumRegisterDto> curriculumList = new ArrayList<>();

    public Course toEntity(Instructor instructorIdx) {


        return Course.builder()
                .name(name)
                .generation(generation)
                .instructor(instructorIdx)
                .build();

    }

}

package com.example.package404.instructor.service;


import com.example.package404.global.exception.InstructorException;
import com.example.package404.global.response.responseStatus.InstructorResponseStatus;
import com.example.package404.instructor.model.Course;
import com.example.package404.instructor.model.Curriculum;
import com.example.package404.instructor.model.Instructor;
import com.example.package404.instructor.model.dto.req.CourseRegister;
import com.example.package404.instructor.model.dto.res.CourseResponseDto;
import com.example.package404.instructor.model.dto.res.CurriculumResponseDto;
import com.example.package404.instructor.model.dto.res.InstructorCourseListResponseDto;
import com.example.package404.instructor.repository.CourseRepository;
import com.example.package404.instructor.repository.CurriculumRepository;
import com.example.package404.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final InstructorService instructorService;
    
    private final CurriculumService curriculumService;


    // 기수 등록
    @Transactional
    public void register(CourseRegister dto, User user) {

        Instructor instructor = instructorService.getInstructorId(user.getIdx());
        if (instructor == null) {
            throw new InstructorException(InstructorResponseStatus.INSTRUCTOR_NOT_FOUND);
        }

        Course course = dto.toEntity(instructor);
        try {
            course = courseRepository.save(course);
        } catch (Exception e) {
            throw new InstructorException(InstructorResponseStatus.COURSE_CREATION_FAILED);
        }

        curriculumService.registerCurriculum(dto.getCurriculumList(), course);
    }

    // 강사의 진행 코스 조회
    public List<InstructorCourseListResponseDto> findIstructorCourse(Long userIdx) {
        List<Course> courses = courseRepository.findByInstructorUserIdx(userIdx);
        if (courses.isEmpty()) {
            throw new InstructorException(InstructorResponseStatus.INSTRUCTOR_NOT_ASSIGNED);
        }

        return courses.stream()
                .map(InstructorCourseListResponseDto::from)
                .collect(Collectors.toList());
    }

    // 학원에서 list 조회
    public List<InstructorCourseListResponseDto> list() {
        List<Course> result = courseRepository.findAllCourses();
        if (result.isEmpty()) {
            throw new InstructorException(InstructorResponseStatus.COURSE_NOT_FOUND);
        }

        return result.stream()
                .map(InstructorCourseListResponseDto::from)
                .collect(Collectors.toList());
    }

    // 교과목별 조회
    public List<CurriculumResponseDto> getCurriculumBySubject(String subject) {
        List<Curriculum> result = curriculumService.getCurriculumBySubject(subject);
        if (result.isEmpty()) {
            throw new InstructorException(InstructorResponseStatus.CURRICULUM_NOT_FOUND);
        }

        return result.stream().map(CurriculumResponseDto::from).collect(Collectors.toList());
    }

    // 코스 상세 조회
    @Transactional(readOnly = true)
    public CourseResponseDto read(int generation) {
        Course course = courseRepository.findAllWithCurriculumListByGeneration(generation);
        if (course == null) {
            throw new InstructorException(InstructorResponseStatus.COURSE_NOT_FOUND);
        }

        return CourseResponseDto.from(course);
    }


    public Course getCourse(Long courseIdx) {
        return courseRepository.findById(courseIdx).orElseThrow();
    }



}

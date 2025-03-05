package com.example.package404.instructor.controller;

import com.example.package404.global.response.BaseResponse;
import com.example.package404.global.response.BaseResponseServiceImpl;
import com.example.package404.global.response.responseStatus.InstructorResponseStatus;
import com.example.package404.instructor.model.Curriculum;
import com.example.package404.instructor.model.dto.req.CourseRegister;
import com.example.package404.instructor.model.dto.res.CurriculumResponseDto;
import com.example.package404.instructor.model.dto.res.InstructorCourseListResponseDto;
import com.example.package404.instructor.model.dto.res.CourseResponseDto;
import com.example.package404.instructor.repository.CourseRepository;
import com.example.package404.instructor.service.CourseService;
import com.example.package404.user.model.User;
import com.example.package404.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/course")
public class CourstController {

    private final BaseResponseServiceImpl baseResponseService;

    private final CourseService courseService;




    //Todo baseResponse

    @PostMapping("/register")
    public BaseResponse register(@AuthenticationPrincipal User user, @RequestBody CourseRegister dto) {


        courseService.register(dto, user);

        return baseResponseService.getSuccessResponse(InstructorResponseStatus.SUCCESS);

    }



    //Todo baseResponse
    // 강사가 맡았던 기수 조회
    @GetMapping("/instructor/{userIdx}")
    public BaseResponse<List<InstructorCourseListResponseDto>> getInstructorCourse(@PathVariable Long userIdx) {
        List<InstructorCourseListResponseDto> response = courseService.findIstructorCourse(userIdx);

        return baseResponseService.getSuccessResponse(response, InstructorResponseStatus.SUCCESS);
    }

    //Todo baseResponse

    //교과목 별 조회
    @GetMapping("/curriculum")
    public  BaseResponse<List<CurriculumResponseDto>>getCurriculumBySubject(@RequestParam String subject) {


        List<CurriculumResponseDto> response = courseService.getCurriculumBySubject(subject);

        return baseResponseService.getSuccessResponse(response, InstructorResponseStatus.SUCCESS);
    }




    //Todo baseResponse

    // 이건 현재 기수들 코스 조회하는거
    @GetMapping("/{generation}")
    public BaseResponse<CourseResponseDto> read(@PathVariable int generation) {
        CourseResponseDto response = courseService.read(generation);
        return baseResponseService.getSuccessResponse(response, InstructorResponseStatus.SUCCESS);
    }



}

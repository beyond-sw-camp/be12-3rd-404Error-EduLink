package com.example.package404.instructor.controller;

import com.example.package404.global.response.BaseResponse;
import com.example.package404.global.response.BaseResponseServiceImpl;
import com.example.package404.global.response.responseStatus.InstructorResponseStatus;
import com.example.package404.instructor.model.dto.req.CourseRegister;
import com.example.package404.instructor.model.dto.res.CurriculumResponseDto;
import com.example.package404.instructor.model.dto.res.InstructorCourseListResponseDto;
import com.example.package404.instructor.model.dto.res.CourseResponseDto;
import com.example.package404.instructor.service.CourseService;
import com.example.package404.user.model.User;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/course")
public class CourseController {

    private final BaseResponseServiceImpl baseResponseService;

    private final CourseService courseService;




    //Todo n+1
    @Operation(summary = "코스 정보 등록", description = "강사가 새로운 코스를 등록하는 기능입니다.")
    @PostMapping("/register")
    public BaseResponse register(@AuthenticationPrincipal User user, @RequestBody CourseRegister dto) {
        courseService.register(dto, user);
        return baseResponseService.getSuccessResponse(InstructorResponseStatus.SUCCESS);
    }



    //Todo n+1
    @Operation(summary = "강사가 맡았던 기수 조회", description = "특정 강사가 맡았던 기수들의 코스를 조회하는 기능입니다.")
    @GetMapping("/instructor/{userIdx}")
    public BaseResponse<List<InstructorCourseListResponseDto>> getInstructorCourse(@PathVariable Long userIdx) {
        List<InstructorCourseListResponseDto> response = courseService.findIstructorCourse(userIdx);
        return baseResponseService.getSuccessResponse(response, InstructorResponseStatus.SUCCESS);
    }


    //Todo n+1
    @Operation(summary = "교과목 별 커리큘럼 조회", description = "특정 교과목에 대한 커리큘럼 정보를 조회하는 기능입니다.")
    @GetMapping("/curriculum")
    public BaseResponse<List<CurriculumResponseDto>> getCurriculumBySubject(@RequestParam String subject) {
        List<CurriculumResponseDto> response = courseService.getCurriculumBySubject(subject);
        return baseResponseService.getSuccessResponse(response, InstructorResponseStatus.SUCCESS);
    }



    //Todo n+1
    @Operation(summary = "기수 별 코스 조회", description = "특정 기수에 대한 코스 정보를 조회하는 기능입니다.")
    @GetMapping("/{generation}")
    public BaseResponse<CourseResponseDto> read(@PathVariable int generation) {
        CourseResponseDto response = courseService.read(generation);
        return baseResponseService.getSuccessResponse(response, InstructorResponseStatus.SUCCESS);
    }



}

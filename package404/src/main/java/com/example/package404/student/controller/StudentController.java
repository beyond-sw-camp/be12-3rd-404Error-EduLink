package com.example.package404.student.controller;

import com.example.package404.global.response.BaseResponse;
import com.example.package404.global.response.BaseResponseService;
import com.example.package404.global.response.BaseResponseServiceImpl;
import com.example.package404.global.response.responseStatus.CommonResponseStatus;
import com.example.package404.student.model.Dto.StudentDetailPageResponse;
import com.example.package404.student.model.Dto.StudentDetailRegisterDto;
import com.example.package404.student.model.Dto.StudentDetailResponseDto;
import com.example.package404.student.model.Dto.StudentResponseDto;
import com.example.package404.student.service.StudentService;
import com.example.package404.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    private final BaseResponseServiceImpl baseResponseService;

    @PostMapping("/register")
    public BaseResponse<Object> register(@AuthenticationPrincipal User user, @RequestBody StudentDetailRegisterDto dto) {
//        User user = new User();
//        user.setIdx(4L);
//        user.setEmail("test4@test.com");
//        user.setPassword("qwer1234");
//        user.setName("lee");
//        user.setRole("ROLE_STUDENT");
        StudentDetailResponseDto response =  studentService.register(dto, user);

        return baseResponseService.getSuccessResponse(response, CommonResponseStatus.CREATED);
    }

    @GetMapping("/read/{idx}")
    public BaseResponse<Object> read(@PathVariable Long idx) {
        StudentResponseDto response = studentService.read(idx);
        return baseResponseService.getSuccessResponse(response, CommonResponseStatus.SUCCESS);
    }

    @GetMapping("/list")
    public BaseResponse<Object> list(int page, int size) {
        StudentDetailPageResponse response = studentService.list(page, size);
        return baseResponseService.getSuccessResponse(response, CommonResponseStatus.SUCCESS);
    }

    @GetMapping("/update/{action}")
    public BaseResponse<Object> update(@AuthenticationPrincipal User user, @PathVariable String action) {
//        User user = new User();
//        user.setIdx(5L);
//        user.setEmail("test2@test.com");
//        user.setPassword("qwer1234");
//        user.setName("lee");
//        user.setRole("ROLE_STUDENT");
        StudentDetailResponseDto response = studentService.update(user, action);

        return baseResponseService.getSuccessResponse(response, CommonResponseStatus.SUCCESS);
    }
}

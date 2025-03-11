package com.example.package404.instructor.controller;

import com.example.package404.global.response.BaseResponse;
import com.example.package404.global.response.BaseResponseServiceImpl;
import com.example.package404.global.response.responseStatus.InstructorResponseStatus;
import com.example.package404.instructor.model.dto.req.CourseRegister;
import com.example.package404.instructor.model.dto.req.CurriculumRegisterDto;
import com.example.package404.instructor.service.CourseService;
import com.example.package404.instructor.service.CurriculumService;
import com.example.package404.user.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "코스 기능", description = "코스 관리 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/curriculum")
public class CurriculumController {
    private final BaseResponseServiceImpl baseResponseService;

    private final CurriculumService curriculumService;

    @Operation(summary = "커리큘럼 정보 등록", description = "강사가 새로운 커리큘럼을 등록하는 기능입니다.")
    @PostMapping("/register")
    public BaseResponse<Object> register(@AuthenticationPrincipal User user, @RequestBody CurriculumRegisterDto dto) {
        curriculumService.register(user, dto);
        return baseResponseService.getSuccessResponse(InstructorResponseStatus.SUCCESS);
    }
}

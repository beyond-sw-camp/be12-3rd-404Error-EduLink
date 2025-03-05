package com.example.package404.manager.controller;

import com.example.package404.global.response.BaseResponse;
import com.example.package404.global.response.BaseResponseServiceImpl;
import com.example.package404.global.response.responseStatus.CommonResponseStatus;
import com.example.package404.instructor.model.dto.res.InstructorResponseDto;
import com.example.package404.manager.model.dto.ManagerResponseDto;
import com.example.package404.manager.model.dto.TestRequestDto;
import com.example.package404.manager.model.dto.TestResponseDto;
import com.example.package404.manager.service.ManagerService;
import com.example.package404.user.model.Dto.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manager")
public class ManagerController {
    private final ManagerService managerService;
    private final BaseResponseServiceImpl baseResponseService;

    @Operation(
            summary = "매니저 목록을 가져온다",
            description = "매니저의 정보와 그 목록들을 가져오는 요청."
    )
    @GetMapping("/list")
    public BaseResponse<List<ManagerResponseDto>> managerList() {
        return managerService.getManagerList();
    }

    @Operation(
            summary = "매니저 정보를 가져온다",
            description = "매니저의 유저 idx를 기반으로 매니저의 정보를 가져오는 요청."
    )
    @GetMapping("/{managerIdx}")
    public BaseResponse<ManagerResponseDto> findManager(@PathVariable Long managerIdx) {
        return managerService.getManager(managerIdx);
    }

    @Operation(
            summary = "강사 목록을 가져온다",
            description = "강사의 정보와 그 목록들을 가져오는 요청."
    )
    @GetMapping("/instructor/list")
    public BaseResponse<List<InstructorResponseDto>> instructorList() {
        return managerService.getInstructorList();
    }

    @Operation(
            summary = "시험을 등록한다",
            description = "시험의 제목, 내용, 과목을 받아 시험을 등록한다"
    )
    @PostMapping("/test/register")
    public BaseResponse<TestResponseDto> registerTest(@RequestBody TestRequestDto dto) {
        return managerService.registerTest(dto);
    }

    @Operation(
            summary = "시험을 수정한다",
            description = "시험의 제목, 내용, 과목을 받아 시험을 수정한다"
    )
    @PostMapping("/test/update/{testIdx}")
    public BaseResponse<TestResponseDto> updateTest(@PathVariable Long testIdx, @RequestBody TestRequestDto dto) {
        return managerService.updateTest(testIdx, dto);
    }

    @Operation(
            summary = "시험을 삭제한다",
            description = "시험의 idx를 받아 시험을 삭제한다"
    )
    @PostMapping("/test/delete/{testIdx}")
    public BaseResponse<TestResponseDto> deleteTest(@PathVariable Long testIdx) {
        return managerService.deleteTest(testIdx);
    }

    @Operation(
            summary = "시험 목록을 가져온다",
            description = "시험의 제목, 내용, 과목을 가져온다"
    )
    @GetMapping("/test/list")
    public BaseResponse<List<TestResponseDto>> testList() {
        return managerService.getTestList();
    }
}

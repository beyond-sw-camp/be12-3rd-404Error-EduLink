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
            summary = "Read board details",
            description = "Fetches the full details of a board including comments."
    )
    @GetMapping("/list")
    public BaseResponse<List<ManagerResponseDto>> managerList() {
        return managerService.getManagerList();
    }

    @Operation(
            summary = "Read board details",
            description = "Fetches the full details of a board including comments."
    )
    @GetMapping("/{managerIdx}")
    public BaseResponse<ManagerResponseDto> findManager(@PathVariable Long managerIdx) {
        return managerService.getManager(managerIdx);
    }

    @Operation(
            summary = "Read board details",
            description = "Fetches the full details of a board including comments."
    )
    @GetMapping("/instructor/list")
    public BaseResponse<List<InstructorResponseDto>> instructorList() {
        return managerService.getInstructorList();
    }

    @PostMapping("/test/register")
    public BaseResponse<TestResponseDto> registerTest(@RequestBody TestRequestDto dto) {
        return managerService.registerTest(dto);
    }

    @PostMapping("/test/update/{testIdx}")
    public BaseResponse<TestResponseDto> updateTest(@PathVariable Long testIdx, @RequestBody TestRequestDto dto) {
        return managerService.updateTest(testIdx, dto);
    }

    @PostMapping("/test/delete/{testIdx}")
    public BaseResponse<TestResponseDto> deleteTest(@PathVariable Long testIdx) {
        return managerService.deleteTest(testIdx);
    }

    @GetMapping("/test/list")
    public BaseResponse<List<TestResponseDto>> testList() {
        return managerService.getTestList();
    }
}

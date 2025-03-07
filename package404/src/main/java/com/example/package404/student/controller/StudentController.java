package com.example.package404.student.controller;

import com.example.package404.global.response.BaseResponse;
import com.example.package404.global.response.BaseResponseService;
import com.example.package404.global.response.BaseResponseServiceImpl;
import com.example.package404.global.response.responseStatus.CommonResponseStatus;
import com.example.package404.student.model.Dto.*;
import com.example.package404.student.service.AttendanceUpdateService;
import com.example.package404.student.service.StudentService;
import com.example.package404.user.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/student")
@Tag(name = "학생 기능", description = "학생 관리 API")
public class StudentController {
    private final StudentService studentService;
    private final AttendanceUpdateService attendanceUpdateService;
    private final BaseResponseServiceImpl baseResponseService;

    @Operation(summary = "학생 정보 등록", description = "학생 정보를 등록하는 기능입니다.")
    @PostMapping("/register")
    public BaseResponse<Object> register(@AuthenticationPrincipal User user, @RequestBody StudentDetailRegisterDto dto) {
        StudentDetailResponseDto response = studentService.register(dto, user);

        return baseResponseService.getSuccessResponse(response, CommonResponseStatus.CREATED);
    }

    @Operation(summary = "학생 상세 조회 기능", description = "학생의 상세 정보를 조회하는 기능입니다.")
    @GetMapping("/read/{idx}")
    public BaseResponse<Object> read(@PathVariable Long idx) {
        StudentResponseDto response = studentService.read(idx);
        return baseResponseService.getSuccessResponse(response, CommonResponseStatus.SUCCESS);
    }

    @Operation(summary = "학생 목록 조회 기능", description = "학생 목록을 조회하는 기능입니다.")
    @GetMapping("/list")
    public BaseResponse<Object> list(int page, int size) {
        StudentDetailPageResponse response = studentService.list(page, size);
        return baseResponseService.getSuccessResponse(response, CommonResponseStatus.SUCCESS);
    }


    @Operation(summary = "학생 출결 체크 기능", description = "학생의 상세 정보를 조회하는 기능입니다.")
    @GetMapping("/getStudentcheck")
    public BaseResponse<StudentDetailResponseDto> read(@AuthenticationPrincipal User user) {
        StudentDetailResponseDto response = studentService.read1(user.getIdx());
        return baseResponseService.getSuccessResponse(response, CommonResponseStatus.SUCCESS);
    }


    @PostMapping("/apply")
    public ResponseEntity<String> applyForLeave(
//            @AuthenticationPrincipal User user,
            @RequestBody AttendanceRequestDto attendanceRequestDto) {
        try {

            User user = User.builder().idx(5L).build();
            studentService.applyForLeave(attendanceRequestDto);
            return ResponseEntity.ok("휴가 신청이 완료되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("휴가 신청에 실패했습니다.");
        }
    }


    @Operation(summary = "학생 출결 업데이트 기능",
            description = "학생의 출결을 업데이트 하는 기능입니다. action: testStatus, perception, attendance, leaveEarly, outing, vacationLeft")
    @GetMapping("/attend/update/{action}")
    public void update(
            @AuthenticationPrincipal User user,
            @PathVariable String action) {
        StudentDetailResponseDto response = studentService.update(user, action);

//        return baseResponseService.getSuccessResponse(response, CommonResponseStatus.SUCCESS);
    }


    @Operation(summary = "학생 출결 배치 업데이트 기능",
            description = "학생들의 출결을 배치 업데이트 하는 기능입니다. action: testStatus, perception, attendance, leaveEarly, outing, vacationLeft")
    @PostMapping("/attend/update/batch/{action}")
    public BaseResponse<Object> updateAttendance(
            @AuthenticationPrincipal User user,
            @PathVariable String action) {

        attendanceUpdateService.enqueueAttendanceUpdate(user.getIdx(), action);
        return baseResponseService.getSuccessResponse("Attendance update enqueued", CommonResponseStatus.SUCCESS);
    }


}

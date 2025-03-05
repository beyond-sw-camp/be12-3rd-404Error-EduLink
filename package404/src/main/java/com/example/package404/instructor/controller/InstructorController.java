package com.example.package404.instructor.controller;


import com.example.package404.global.response.BaseResponse;
import com.example.package404.global.response.BaseResponseServiceImpl;
import com.example.package404.global.response.responseStatus.InstructorResponseStatus;
import com.example.package404.instructor.model.dto.req.InstructorRequestDto;
import com.example.package404.instructor.model.dto.req.UpdateUserInstructorDto;
import com.example.package404.instructor.model.dto.res.InstructorResponseDto;
import com.example.package404.instructor.repository.InstructorRepository;
import com.example.package404.instructor.service.InstructorService;
import com.example.package404.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/instructor")
public class InstructorController {

    private final BaseResponseServiceImpl baseResponseService;


    private final InstructorService instructorService;

    //삭제해야할것
//     private final InstructorRepository userRepository;

//    @GetMapping("/{instructorId}")
//    public ResponseEntity<InstructorResDto> getInstructor(@PathVariable Long instructorId) {
//        InstructorResDto instructor = instructorService.getInstructorById(instructorId);
//        return ResponseEntity.ok(instructor);
//    }


    //


    // Todo n+1 처리해야함
    // 강사가 자기 정보 불러오기
    // o
    @GetMapping("/edit")
    public BaseResponse<InstructorResponseDto> getInstructor(/*@AuthenticationPrincipal User user*/) {
        User user = User.builder().idx(3L).build();

        InstructorResponseDto response = instructorService.getInstructor(user.getIdx());


        return baseResponseService.getSuccessResponse(response, InstructorResponseStatus.SUCCESS);
    }
    //Todo baseResponse
    // 아직 안함
    // 강사 정보 수정
    @PostMapping("/edit")
    public BaseResponse SetInfo(@RequestBody UpdateUserInstructorDto dto, @AuthenticationPrincipal User user) {
        instructorService.setInfo( dto, user);

        return baseResponseService.getSuccessResponse(InstructorResponseStatus.SUCCESS);
    }


    // Todo n+1 해야함
    // 강사 정보 조회
    //
    @GetMapping("/instructors")
    public BaseResponse<List<InstructorResponseDto>> getAllInstructorInfo() {

        List<InstructorResponseDto> responseDtoList = instructorService.instructor_list2();


        return baseResponseService.getSuccessResponse(responseDtoList, InstructorResponseStatus.SUCCESS);
    }


}

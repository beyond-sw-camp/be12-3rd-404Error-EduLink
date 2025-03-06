package com.example.package404.user.controller;

import com.example.package404.global.response.BaseResponse;
import com.example.package404.global.response.BaseResponseServiceImpl;
import com.example.package404.global.response.responseStatus.CommonResponseStatus;
import com.example.package404.global.response.responseStatus.InstructorResponseStatus;
import com.example.package404.global.response.responseStatus.UserResponseStatus;
import com.example.package404.instructor.model.dto.res.InstructorResponseDto;
import com.example.package404.instructor.service.InstructorService;
import com.example.package404.student.model.Dto.StudentResponseDto;
import com.example.package404.student.service.StudentService;
import com.example.package404.user.model.Dto.UserInsSignUpDto;
import com.example.package404.user.model.User;
import com.example.package404.user.service.UserService;
import com.example.package404.user.model.Dto.UserRequestDto;
import com.example.package404.user.model.Dto.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;
    private final BaseResponseServiceImpl baseResponseService;
    private final InstructorService instructorService;
    private final StudentService studentService;

    @Operation(summary = "회원가입", description = "회원가입 하는 API")
    @PostMapping("/signup/{role}")
    public BaseResponse<UserResponseDto.SignupResponse> createUser(@PathVariable String role, @RequestBody UserRequestDto.SignupRequest dto) {
        UserResponseDto.SignupResponse response = userService.signup(dto, role);

        return baseResponseService.getSuccessResponse(response, UserResponseStatus.SUCCESS);
    }

 
//    @PostMapping("/signup2/{role}")
//    public BaseResponse<UserResponseDto.SignupResponse> createUser2(@PathVariable String role, @RequestBody UserRequestDto.SignupRequest dto) {
//        UserResponseDto.SignupResponse response = userService.signup2(dto, role);
//
//        return baseResponseService.getSuccessResponse(response, UserResponseStatus.SUCCESS);
//    }




    @PostMapping("/CustomInstructorSignup/{role}")
    public void createUser2(@PathVariable String role, @RequestBody UserInsSignUpDto dto) {
        userService.CustomInstructorSignup(dto, role);

     }


    @Operation(summary = "회원정보 조회", description = "로그인한 사용자의 정보를 조회합니다.")
    @GetMapping("/info")
    public BaseResponse<?> getUserInfo(@AuthenticationPrincipal User user) {
        if(user == null) {
            throw new UsernameNotFoundException("로그인한 사용자가 없습니다.");
        }

        // TODO: service로 빼야 함
        String role = user.getRole();
        if ("MANAGER".equalsIgnoreCase(role)) {
            return baseResponseService.getSuccessResponse(
                    UserResponseDto.SignupResponse.from(user),
                    UserResponseStatus.SUCCESS
            );
        } else if ("INSTRUCTOR".equalsIgnoreCase(role)) {
            InstructorResponseDto instructorInfo = instructorService.getInstructor(user.getIdx());
            return baseResponseService.getSuccessResponse(
                    instructorInfo,
                    InstructorResponseStatus.SUCCESS
            );
        } else if ("STUDENT".equalsIgnoreCase(role)) {
            StudentResponseDto studentInfo = studentService.readAllInfo(user.getIdx());
            return baseResponseService.getSuccessResponse(
                    studentInfo,
                    CommonResponseStatus.SUCCESS
            );
        } else {
            return baseResponseService.getSuccessResponse(
                    UserResponseDto.SignupResponse.from(user),
                    UserResponseStatus.SUCCESS
            );
        }
    }
 }

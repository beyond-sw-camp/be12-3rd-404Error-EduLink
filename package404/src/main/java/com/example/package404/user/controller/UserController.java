package com.example.package404.user.controller;

import com.example.package404.global.response.BaseResponse;
import com.example.package404.global.response.BaseResponseServiceImpl;
import com.example.package404.global.response.responseStatus.UserResponseStatus;
import com.example.package404.user.model.Dto.UserInsSignUpDto;
import com.example.package404.user.service.UserService;
import com.example.package404.user.model.Dto.UserRequestDto;
import com.example.package404.user.model.Dto.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;
    private final BaseResponseServiceImpl baseResponseService;

    @Operation(summary = "회원가입", description = "회원가입 하는 API")
    @PostMapping("/signup/{role}")
    public BaseResponse<UserResponseDto.SignupResponse> createUser(@PathVariable String role, @RequestBody UserRequestDto.SignupRequest dto) {
        UserResponseDto.SignupResponse response = userService.signup(dto, role);

        return baseResponseService.getSuccessResponse(response, UserResponseStatus.SUCCESS);
    }

 
    @PostMapping("/signup2/{role}")
    public BaseResponse<UserResponseDto.SignupResponse> createUser2(@PathVariable String role, @RequestBody UserRequestDto.SignupRequest dto) {
        UserResponseDto.SignupResponse response = userService.signup2(dto, role);

        return baseResponseService.getSuccessResponse(response, UserResponseStatus.SUCCESS);
    }




    @PostMapping("/CustomInstructorSignup/{role}")
    public void createUser2(@PathVariable String role, @RequestBody UserInsSignUpDto dto) {
        userService.CustomInstructorSignup(dto, role);

     }



 }

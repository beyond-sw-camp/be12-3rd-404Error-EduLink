package com.example.package404.user.controller;

import com.example.package404.global.response.BaseResponse;
import com.example.package404.global.response.BaseResponseServiceImpl;
import com.example.package404.global.response.responseStatus.CommonResponseStatus;
import com.example.package404.global.response.responseStatus.UserResponseStatus;
import com.example.package404.user.model.Dto.UserInsSignUpDto;
import com.example.package404.user.model.User;
import com.example.package404.user.service.UserService;
import com.example.package404.user.model.Dto.UserRequestDto;
import com.example.package404.user.model.Dto.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
@Tag(name = "사용자 기능", description = "사용자 관리 API")
public class UserController {

    private final UserService userService;
    private final BaseResponseServiceImpl baseResponseService;

    @Operation(summary = "회원가입", description = "사용자가 회원가입 하는 기능입니다.")
    @ApiResponse(responseCode = "200", description = "회원가입 성공")
    @ApiResponse(responseCode = "400", description = "회원가입 실패")
    @PostMapping("/signup/{role}")
    public BaseResponse<UserResponseDto.SignupResponse> createUser(@PathVariable String role, @RequestBody UserRequestDto.SignupRequest dto) {
        UserResponseDto.SignupResponse response = userService.signup(dto, role);

        return baseResponseService.getSuccessResponse(response, UserResponseStatus.SUCCESS);
    }

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

        Object userInfo = userService.getUserInfo(user);
        return baseResponseService.getSuccessResponse(userInfo, CommonResponseStatus.SUCCESS);
    }
 }

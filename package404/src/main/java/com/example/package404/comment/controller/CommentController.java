package com.example.package404.comment.controller;

import com.example.package404.comment.model.dto.CommentDeleteResponse;
import com.example.package404.comment.model.dto.CommentRequestDto;
import com.example.package404.comment.model.dto.CommentResponseDto;
import com.example.package404.comment.service.CommentService;
import com.example.package404.global.response.BaseResponse;
import com.example.package404.global.response.BaseResponseService;
import com.example.package404.global.response.responseStatus.BaseResponseStatus;
import com.example.package404.global.response.responseStatus.CommonResponseStatus;
import com.example.package404.global.response.responseStatus.UserResponseStatus;
import com.example.package404.user.model.User;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;
    private final BaseResponseService baseResponseService;
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Operation(
            summary = "댓글 작성하기",
            description = "boardIdx를 전달받아 전달 받은 boardIdx의 게시글에 댓글을 작성합니다."
    )
    @PostMapping("/register/{boardIdx}")
    public BaseResponse<Object> register(@AuthenticationPrincipal User loginUser, @PathVariable Long boardIdx, @RequestBody CommentRequestDto dto) {
        if (loginUser == null) {
            return baseResponseService.getFailureResponse(UserResponseStatus.USER_NOT_FOUND);
        }


        CommentResponseDto response = commentService.register(loginUser, boardIdx, dto);
        return baseResponseService.getSuccessResponse(response, CommonResponseStatus.CREATED);
    }

    @Operation(
            summary = "댓글 삭제하기",
            description = "commentIdx를 전달받아 전달 받은 boardIdx의 게시글에 댓글을 삭제합니다."
    )
    @DeleteMapping("/delete/{commentIdx}")
    public BaseResponse<Object> delete(@AuthenticationPrincipal User loginUser, @PathVariable Long commentIdx) {
        CommentDeleteResponse response = commentService.delete(loginUser, commentIdx);
        return baseResponseService.getSuccessResponse(response, CommonResponseStatus.DELETED);
    }

}

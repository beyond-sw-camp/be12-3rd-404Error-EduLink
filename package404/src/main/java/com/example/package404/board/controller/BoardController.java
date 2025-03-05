package com.example.package404.board.controller;

import com.example.package404.board.model.dto.BoardPageResponse;
import com.example.package404.board.model.dto.BoardReadResponseDto;
import com.example.package404.board.model.dto.BoardRequestDto;
import com.example.package404.board.model.dto.BoardResponseDto;
import com.example.package404.board.service.BoardService;
import com.example.package404.global.response.BaseResponse;
import com.example.package404.global.response.BaseResponseServiceImpl;
import com.example.package404.global.response.responseStatus.CommonResponseStatus;
import com.example.package404.user.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final BaseResponseServiceImpl baseResponseService;

    @Operation(
            summary = "게시글 등록",
            description = "boardType을 통해 어떤 게시판에 글을 작성할지 지정한 뒤, 제목, 내용, 첨부파일과 함께 글을 작성합니다."
    )
    @PostMapping("/register/{boardType}")
    public BaseResponse<Object> register(@AuthenticationPrincipal User loginUser, @RequestBody BoardRequestDto boardRequestDto, @PathVariable int boardType) {
        BoardResponseDto response = boardService.register(loginUser, boardRequestDto, boardType);
        return baseResponseService.getSuccessResponse(response, CommonResponseStatus.CREATED);
    }

    @Operation(
            summary = "게시글 상세보기",
            description = "boardIdx를 전달받아 게시글 하나의 정보를 확인합니다."
    )
    @GetMapping("/read/{boardIdx}")
    public BaseResponse<Object> read(@PathVariable Long boardIdx) {
        BoardReadResponseDto response = boardService.read(boardIdx);
        return baseResponseService.getSuccessResponse(response, CommonResponseStatus.SUCCESS);
    }

    @Operation(
            summary = "게시글 리스트보기",
            description = "boardIdx를 전달받아 게시글 하나의 정보를 확인합니다."
    )
    @GetMapping("/list/{boardType}")
    public BaseResponse<Object> getBoardList(@PathVariable int boardType,int page, int size) {
        BoardPageResponse response = boardService.getBoardList(boardType, page, size);
        return baseResponseService.getSuccessResponse(response, CommonResponseStatus.SUCCESS);
    }

}

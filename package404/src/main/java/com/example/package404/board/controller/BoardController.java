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
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;
    private final BaseResponseServiceImpl baseResponseService;

    @PostMapping("/register/{boardType}")
    public BaseResponse<Object> register(@AuthenticationPrincipal User loginUser, @RequestBody BoardRequestDto boardRequestDto, @PathVariable int boardType) {
        System.out.println("유저 잘나옴 >? : " + loginUser);
        BoardResponseDto response = boardService.register(loginUser, boardRequestDto, boardType);
        return baseResponseService.getSuccessResponse(response, CommonResponseStatus.CREATED);
    }

    @GetMapping("/read/{boardIdx}")
    public BaseResponse<Object> read(@PathVariable Long boardIdx) {
        BoardReadResponseDto response = boardService.read(boardIdx);
        return baseResponseService.getSuccessResponse(response, CommonResponseStatus.SUCCESS);
    }

    @GetMapping("/list/{boardType}")
    public BaseResponse<Object> getBoardList(@PathVariable int boardType,int page, int size) {
        BoardPageResponse response = boardService.getBoardList(boardType, page, size);
        return baseResponseService.getSuccessResponse(response, CommonResponseStatus.SUCCESS);
    }

}

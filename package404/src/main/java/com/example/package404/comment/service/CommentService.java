package com.example.package404.comment.service;

import com.example.package404.board.model.Board;
import com.example.package404.board.repository.BoardRepository;
import com.example.package404.comment.model.Comment;
import com.example.package404.comment.model.dto.CommentRequestDto;
import com.example.package404.comment.model.dto.CommentResponseDto;
import com.example.package404.comment.repository.CommentRepository;
import com.example.package404.global.exception.CommentException;
import com.example.package404.global.response.BaseResponseService;
import com.example.package404.global.response.responseStatus.CommentResponseStatus;
import com.example.package404.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final BaseResponseService baseResponseService;

    public CommentResponseDto register(User loginUser, Long boardIdx, CommentRequestDto dto) {
        Board board = boardRepository.findById(boardIdx)
                .orElseThrow(() -> new CommentException(CommentResponseStatus.INVALID_BOARD_ID)); // 예외 처리 추가
        try {
            Comment comment = commentRepository.save(dto.toEntity(loginUser, board));
            return CommentResponseDto.from(comment);
        } catch (Exception e) {
            throw new CommentException(CommentResponseStatus.COMMENT_CREATION_FAILED);
        }

    }
}

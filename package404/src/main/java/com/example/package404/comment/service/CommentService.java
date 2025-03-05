package com.example.package404.comment.service;

import com.example.package404.board.model.Board;
import com.example.package404.board.model.dto.BoardDeleteResponse;
import com.example.package404.board.repository.BoardRepository;
import com.example.package404.comment.model.Comment;
import com.example.package404.comment.model.dto.*;
import com.example.package404.comment.repository.CommentRepository;
import com.example.package404.global.exception.BoardException;
import com.example.package404.global.exception.CommentException;
import com.example.package404.global.response.BaseResponseService;
import com.example.package404.global.response.responseStatus.BoardResponseStatus;
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

    public CommentDeleteResponse delete(User loginUser, Long commentIdx) {
        Comment comment = commentRepository.findById(commentIdx)
                .orElseThrow(() -> new CommentException(CommentResponseStatus.INVALID_COMMENT_ID));
        if(!comment.getUser().getIdx().equals(loginUser.getIdx())){
            throw new CommentException(CommentResponseStatus.COMMENT_ACCESS_DENIED);
        }
        commentRepository.delete(comment);

        return CommentDeleteResponse.from(comment.getIdx());
    }

    public CommentUpdateResponse update(User loginUser, Long commentIdx, CommentUpdateRequest dto) {
        Comment comment = commentRepository.findById(commentIdx)
                .orElseThrow(() -> new CommentException(CommentResponseStatus.INVALID_COMMENT_ID));


        if (!comment.getUser().getIdx().equals(loginUser.getIdx())) {
            throw new CommentException(CommentResponseStatus.COMMENT_ACCESS_DENIED);
        }

        comment.updateContent(dto.getContent());

        Comment updatedComment = commentRepository.save(comment);

        return CommentUpdateResponse.from(updatedComment);
    }
}

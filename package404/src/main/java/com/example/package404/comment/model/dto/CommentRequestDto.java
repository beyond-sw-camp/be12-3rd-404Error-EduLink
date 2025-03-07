package com.example.package404.comment.model.dto;

import com.example.package404.board.model.Board;
import com.example.package404.comment.model.Comment;
import com.example.package404.user.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentRequestDto {
    @Schema(description = "댓글의 내용", example = "댓글 내용 입니다.")
    private String content;

    public Comment toEntity(User loginUser, Board board) {
        return Comment.builder()
                .content(content)
                .user(loginUser)
                .board(board)
                .createdDate(LocalDateTime.now())
                .modifiedDate(null).build();
    }

}

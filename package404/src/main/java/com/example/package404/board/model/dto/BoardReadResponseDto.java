package com.example.package404.board.model.dto;

import com.example.package404.board.model.Board;
import com.example.package404.comment.model.Comment;
import com.example.package404.comment.model.dto.CommentReadResponseDto;
import com.example.package404.comment.model.dto.CommentResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardReadResponseDto {
    private String title;
    private String content;
    private String writer;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    private List<String> imageUrls;

    private List<CommentReadResponseDto> comments;

    public static BoardReadResponseDto from(Board board) {
        return BoardReadResponseDto.builder()
                .title(board.getTitle())
                .content(board.getContent())
                .writer(board.getUser().getName()).comments(board.getComments().stream()
                        .map(comment -> CommentReadResponseDto.builder()
                                .idx(comment.getIdx())
                                .content(comment.getContent())
                                .createdDate(comment.getCreatedDate())
                                .modifiedDate(comment.getModifiedDate())
                                .writer(comment.getUser().getName())
                                .build())
                        .collect(Collectors.toList()))
                .imageUrls(
                        board.getImageList() == null ? List.of() : board.getImageList().stream()
                                .map(image -> image.getUrl()).toList()
                ).createdDate(board.getCreatedDate()).modifiedDate(board.getModifiedDate()).build();
    }

}

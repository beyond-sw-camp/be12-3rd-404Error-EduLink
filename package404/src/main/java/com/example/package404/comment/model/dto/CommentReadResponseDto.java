package com.example.package404.comment.model.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class CommentReadResponseDto {
    private Long idx;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime  modifiedDate;
    private String writer;
}

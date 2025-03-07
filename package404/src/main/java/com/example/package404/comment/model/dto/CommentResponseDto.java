package com.example.package404.comment.model.dto;

import com.example.package404.comment.model.Comment;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentResponseDto {
    private String writer;


    public static CommentResponseDto from(Comment comment){
        return CommentResponseDto.builder().writer(comment.getUser().getName()).build();
    }
}

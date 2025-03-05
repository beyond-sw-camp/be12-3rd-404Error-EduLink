package com.example.package404.comment.model.dto;

import com.example.package404.comment.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CommentUpdateRequest {
    private String content;
    public Comment toEntity() {
        return Comment.builder().content(content).build();
    }
}

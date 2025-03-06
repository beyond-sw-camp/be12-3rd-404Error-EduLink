package com.example.package404.comment.model.dto;

import com.example.package404.comment.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentUpdateResponse {
    private Long commentIdx;
    private LocalDateTime modifiedDate;
    public static CommentUpdateResponse from(Comment comment) {
        return CommentUpdateResponse.builder().commentIdx(comment.getIdx()).modifiedDate(comment.getModifiedDate()).build();
    }
}

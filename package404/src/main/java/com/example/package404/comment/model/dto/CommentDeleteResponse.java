package com.example.package404.comment.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDeleteResponse {
    private Long commentIdx;
    public static CommentDeleteResponse from(Long commentIdx) {
        return CommentDeleteResponse.builder().commentIdx(commentIdx).build();
    }
}

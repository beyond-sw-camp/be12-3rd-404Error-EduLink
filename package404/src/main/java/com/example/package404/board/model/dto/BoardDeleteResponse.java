package com.example.package404.board.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BoardDeleteResponse {
    private Long boardIdx;
    public static BoardDeleteResponse from(Long boardIdx) {
        return BoardDeleteResponse.builder().boardIdx(boardIdx).build();
    }
}

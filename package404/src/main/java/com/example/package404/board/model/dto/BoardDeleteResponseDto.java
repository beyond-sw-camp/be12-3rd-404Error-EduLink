package com.example.package404.board.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class BoardDeleteResponseDto {
    private Long boardIdx;
    public static BoardDeleteResponseDto from(Long boardIdx) {
        return BoardDeleteResponseDto.builder().boardIdx(boardIdx).build();
    }
}

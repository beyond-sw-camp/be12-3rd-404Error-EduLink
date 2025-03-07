package com.example.package404.board.model.dto;

import com.example.package404.board.model.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardResponseDto {
    private Long idx;
    private String title;
    private String writer;
    private LocalDateTime createdDate;

    public static BoardResponseDto from(Board board) {
        return BoardResponseDto.builder().idx(board.getIdx()).title(board.getTitle()).writer(board.getUser().getName()).createdDate(board.getCreatedDate()).build();
    }

}

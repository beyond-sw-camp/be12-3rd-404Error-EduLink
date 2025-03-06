package com.example.package404.board.model.dto;

import com.example.package404.board.model.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardResponseDto {
    private String title;
    private String writer;
    private LocalDateTime createdDate;
    private List<String> preSignedUrls;

    public static BoardResponseDto from(Board board) {
        return BoardResponseDto.builder()
                .title(board.getTitle())
                .writer(board.getUser().getName())
                .createdDate(board.getCreatedDate())
                .preSignedUrls(null)
                .build();
    }


    public static BoardResponseDto from(Board board, List<String> preSignedUrls) {
        return BoardResponseDto.builder()
                .title(board.getTitle())
                .writer(board.getUser().getName())
                .createdDate(board.getCreatedDate())
                .preSignedUrls(preSignedUrls)
                .build();
    }
}

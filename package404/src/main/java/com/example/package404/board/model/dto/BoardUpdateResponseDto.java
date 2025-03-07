package com.example.package404.board.model.dto;

import com.example.package404.board.model.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardUpdateResponseDto {
    private Long idx;
    private String title;
    private LocalDateTime modifiedDate;
    private List<String> newFileUrls;

    public static BoardUpdateResponseDto from(Board board, List<String> newFileUrls) {
        return BoardUpdateResponseDto.builder().idx(board.getIdx()).title(board.getTitle()).modifiedDate(board.getModifiedDate()).newFileUrls(newFileUrls).build();
    }

}

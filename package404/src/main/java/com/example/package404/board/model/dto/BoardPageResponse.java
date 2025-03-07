package com.example.package404.board.model.dto;

import com.example.package404.board.model.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardPageResponse {
    private int page;
    private int size;
    private long totalElements;
    private int totalPages;
    private boolean hasNext;
    private boolean hasPrevious;

    private List<BoardResponseDto> boardList;

    public static BoardPageResponse from(Page<Board> boardPage) {
        return BoardPageResponse.builder()
                .page(boardPage.getNumber())
                .size(boardPage.getSize())
                .totalElements(boardPage.getTotalElements())
                .totalPages(boardPage.getTotalPages())
                .hasNext(boardPage.hasNext())
                .hasPrevious(boardPage.hasPrevious())
                .boardList(boardPage.stream().map(BoardResponseDto::from).collect(Collectors.toList()))
                .build();
    }
}

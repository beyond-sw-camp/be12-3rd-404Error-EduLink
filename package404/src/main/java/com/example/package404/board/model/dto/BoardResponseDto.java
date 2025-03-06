package com.example.package404.board.model.dto;

import com.example.package404.board.model.Board;
import com.example.package404.board.model.BoardImage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardResponseDto {
    private String title;
    private String writer;
    private LocalDateTime createdDate;
    private List<String> preSignedUrls;
    private List<String> imageKeys;

    public static BoardResponseDto from(Board board) {
        return BoardResponseDto.builder()
                .title(board.getTitle())
                .writer(board.getUser().getName())
                .createdDate(board.getCreatedDate())
                .preSignedUrls(null)
                .build();
    }


    public static BoardResponseDto from(Board board, List<String> preSignedUrls) {
        List<String> imageKeys = board.getImageList() != null
                ? board.getImageList().stream()
                .map(BoardImage::getUrl)
                .collect(Collectors.toList())
                : Collections.emptyList();

        return BoardResponseDto.builder()
                .title(board.getTitle())
                .writer(board.getUser().getName())
                .createdDate(board.getCreatedDate())
                .preSignedUrls(preSignedUrls)
                .imageKeys(imageKeys)
                .build();
    }
}

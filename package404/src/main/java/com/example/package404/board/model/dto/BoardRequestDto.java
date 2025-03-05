package com.example.package404.board.model.dto;

import com.example.package404.board.model.Board;
import com.example.package404.user.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardRequestDto {
    private String title;
    private String content;

    public Board toEntity(User loginUser, int boardType) {
        return Board.builder()
                .title(title)
                .content(content)
                .user(loginUser)
                .createdDate(LocalDateTime.now())  // 현재 시간 설정
                .modifiedDate(null)  // 수정 시간은 null
                .boardType(boardType)
                .build();
    }
}

package com.example.package404.board.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardUpdateRequestDto {
    private String title;
    private String content;
    private List<String> addFiles;   // 새로 추가할 파일 리스트
    private List<String> deleteFiles; // 삭제할 파일 리스트
}

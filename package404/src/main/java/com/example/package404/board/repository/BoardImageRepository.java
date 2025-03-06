package com.example.package404.board.repository;

import com.example.package404.board.model.Board;
import com.example.package404.board.model.BoardImage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardImageRepository extends JpaRepository<BoardImage, Long> {
    default void saveAllImages(List<String> uploadFilePaths, Board board) {
        List<BoardImage> boardImages = uploadFilePaths.stream()
                .map(path -> BoardImage.builder()
                        .url(path)
                        .board(board)
                        .build())
                .toList();
        saveAll(boardImages);
    }

    @Query("SELECT bi.url FROM BoardImage bi WHERE bi.board = :board")
    List<String> findUrlsByBoard(@Param("board") Board board);

    void deleteByBoard(Board board);

}

package com.example.package404.board.service;

import com.example.package404.board.model.Board;
import com.example.package404.board.model.dto.*;
import com.example.package404.board.repository.BoardImageRepository;
import com.example.package404.board.repository.BoardRepository;
import com.example.package404.board.repository.PreSignedCloudImageRepository;
import com.example.package404.global.exception.BoardException;
import com.example.package404.global.response.responseStatus.BoardResponseStatus;
import com.example.package404.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final PreSignedCloudImageRepository preSignedCloudImageRepository;
    private final BoardImageRepository boardImageRepository;

    public BoardResponseDto register(User loginUser,BoardRequestDto boardRequestDto, int boardType) {
        if (boardRequestDto == null) {
            throw new BoardException(BoardResponseStatus.INVALID_BOARD_ID); // 잘못된 요청 데이터
        }

        try {
            Board board = boardRepository.save(boardRequestDto.toEntity(loginUser, boardType));

            List<String> uploadFilePaths = new ArrayList<>();
            List<String> preSignedUrls = new ArrayList<>();
            for (String file : boardRequestDto.getFiles()) {
                String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd/"));
                String fileName = date + UUID.randomUUID() + "_" + file;

                String preSignedUrl = preSignedCloudImageRepository.generatePreSignedUrl(fileName, "image/png");
                preSignedUrls.add(preSignedUrl);
                uploadFilePaths.add(fileName);
            }


            boardImageRepository.saveAllImages(uploadFilePaths, board);


            return BoardResponseDto.from(board);
        } catch (Exception e) {
            throw new BoardException(BoardResponseStatus.BOARD_CREATION_FAILED); // 게시글 저장 실패
        }
    }

    public BoardReadResponseDto read(Long boardIdx) {
        if (boardIdx == null || boardIdx <= 0) {
            throw new BoardException(BoardResponseStatus.INVALID_BOARD_ID); // 유효하지 않은 ID
        }

        Board board = boardRepository.getBoardByIdx(boardIdx);
        if (board == null) {
            throw new BoardException(BoardResponseStatus.BOARD_NOT_FOUND); // 게시글 없음
        }

        return BoardReadResponseDto.from(board);
    }

    public BoardPageResponse getBoardList(int boardType, int page, int size) {
        if (boardType < 0) {
            throw new BoardException(BoardResponseStatus.INVALID_PAGE); // 잘못된 게시판 타입
        }
        if (page < 0 || size <= 0) {
            throw new BoardException(BoardResponseStatus.INVALID_PAGE); // 잘못된 페이지 요청
        }

        Page<Board> boardList = boardRepository.findAllByBoardType(PageRequest.of(page, size), boardType);
        if (boardList.isEmpty()) {
            throw new BoardException(BoardResponseStatus.BOARD_NOT_FOUND); // 게시판에 게시글 없음
        }

        return BoardPageResponse.from(boardList);
    }

    public BoardDeleteResponse deleteBoard(User loginUser, Long boardIdx) {
        Board board = boardRepository.findById(boardIdx)
                .orElseThrow(() -> new BoardException(BoardResponseStatus.INVALID_BOARD_ID));
        if (!board.getUser().getIdx().equals(loginUser.getIdx())) {
            throw new BoardException(BoardResponseStatus.BOARD_ACCESS_DENIED);  // 권한 없음 예외 발생
        }
        boardRepository.delete(board);
        return BoardDeleteResponse.from(board.getIdx());
    }
}

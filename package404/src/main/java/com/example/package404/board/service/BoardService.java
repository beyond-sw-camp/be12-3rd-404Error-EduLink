package com.example.package404.board.service;

import com.example.package404.board.model.Board;
import com.example.package404.board.model.BoardImage;
import com.example.package404.board.model.dto.*;
import com.example.package404.board.repository.BoardImageRepository;
import com.example.package404.board.repository.BoardRepository;
import com.example.package404.common.s3.PreSignedUrlService;
import com.example.package404.common.s3.S3Service;
import com.example.package404.global.exception.BoardException;
import com.example.package404.global.response.responseStatus.BoardResponseStatus;
import com.example.package404.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardImageRepository boardImageRepository;
    private final PreSignedUrlService preSignedUrlService;
    private final S3Service s3Service;

    public BoardResponseDto register(User loginUser, BoardRequestDto boardRequestDto, int boardType) {
        if (boardRequestDto == null) {
            throw new BoardException(BoardResponseStatus.INVALID_BOARD_ID);
        }

        try {

            Board board = boardRepository.save(boardRequestDto.toEntity(loginUser, boardType));

            List<String> uploadFilePaths = new ArrayList<>();
            List<String> preSignedUrls = new ArrayList<>();

            for (String fileName : boardRequestDto.getFiles()) {
                // 파일 이름 변환
                String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd/"));
                String newFileName = date + UUID.randomUUID() + "_" + fileName;

                // Presigned URL 생성
                String preSignedUrl = preSignedUrlService.generatePreSignedUrl(newFileName, "image/png");
                preSignedUrls.add(preSignedUrl);
                uploadFilePaths.add(newFileName);
            }

            // S3 업로드 완료 후 DB에 이미지 저장
            boardImageRepository.saveAllImages(uploadFilePaths, board);

            // Presigned URL을 포함하여 응답 반환
            return BoardResponseDto.from(board, preSignedUrls);
        } catch (Exception e) {
            throw new BoardException(BoardResponseStatus.BOARD_CREATION_FAILED);
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

    @Transactional
    public BoardDeleteResponseDto deleteBoard(User loginUser, Long boardIdx) {
        Board board = boardRepository.findById(boardIdx)
                .orElseThrow(() -> new BoardException(BoardResponseStatus.INVALID_BOARD_ID));

        if (!Objects.equals(board.getUser().getIdx(), loginUser.getIdx())) {
            throw new BoardException(BoardResponseStatus.BOARD_ACCESS_DENIED);
        }

        List<String> fileUrls = boardImageRepository.findUrlsByBoard(board);

        if (!fileUrls.isEmpty()) {
            s3Service.deleteFiles(fileUrls);
        }

        boardImageRepository.deleteByBoard(board);

        boardRepository.delete(board);

        return BoardDeleteResponseDto.from(board.getIdx());
    }


    @Transactional
    public BoardUpdateResponseDto updateBoard(User loginUser, Long boardIdx, BoardUpdateRequestDto requestDto) {
        Board board = boardRepository.findById(boardIdx)
                .orElseThrow(() -> new BoardException(BoardResponseStatus.INVALID_BOARD_ID));

        if (!Objects.equals(board.getUser().getIdx(), loginUser.getIdx())) {
            throw new BoardException(BoardResponseStatus.BOARD_ACCESS_DENIED);
        }

        if (requestDto.getDeleteFiles() != null && !requestDto.getDeleteFiles().isEmpty()) {
            s3Service.deleteFiles(requestDto.getDeleteFiles()); // S3 파일 삭제
            boardImageRepository.deleteByBoardAndUrls(board, requestDto.getDeleteFiles()); // DB에서도 삭제
        }

        List<String> newFileUrls = new ArrayList<>();
        if (requestDto.getAddFiles() != null && !requestDto.getAddFiles().isEmpty()) {
            List<BoardImage> boardImages = new ArrayList<>();
            for (String fileName : requestDto.getAddFiles()) {
                String preSignedUrl = preSignedUrlService.generatePreSignedUrl(fileName, "image/png");
                newFileUrls.add(preSignedUrl);

                boardImages.add(BoardImage.builder()
                        .url(fileName)
                        .board(board)
                        .build());
            }
            boardImageRepository.saveAll(boardImages);
        }

        board.update(requestDto.getTitle(), requestDto.getContent());

        return BoardUpdateResponseDto.from(board, newFileUrls);
    }
}

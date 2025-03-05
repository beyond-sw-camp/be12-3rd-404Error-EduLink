package com.example.package404.global.exception;

import com.example.package404.global.response.responseStatus.BoardResponseStatus;

public class BoardException extends BaseException {
    public BoardException(BoardResponseStatus status) {
        super(status);
    }
}

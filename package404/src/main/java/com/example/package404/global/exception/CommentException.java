package com.example.package404.global.exception;

import com.example.package404.global.response.responseStatus.CommentResponseStatus;

public class CommentException extends BaseException {
    public CommentException(CommentResponseStatus status) {
        super(status);
    }
}

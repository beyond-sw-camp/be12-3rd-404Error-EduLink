package com.example.package404.global.exception;

import com.example.package404.global.response.responseStatus.StudentResponseStatus;

public class StudentException extends BaseException {
    public StudentException(StudentResponseStatus status) {
        super(status);
    }
}

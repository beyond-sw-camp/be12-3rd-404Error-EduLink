package com.example.package404.global.exception;

import com.example.package404.global.response.responseStatus.InstructorResponseStatus;

public class InstructorException extends BaseException {
    public InstructorException(InstructorResponseStatus status) {
        super(status);
    }
}
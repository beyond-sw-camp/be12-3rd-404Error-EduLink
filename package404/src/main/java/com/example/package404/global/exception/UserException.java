package com.example.package404.global.exception;

import com.example.package404.global.response.responseStatus.UserResponseStatus;

public class UserException extends BaseException {
    public UserException(UserResponseStatus status) {
        super(status);
    }
}

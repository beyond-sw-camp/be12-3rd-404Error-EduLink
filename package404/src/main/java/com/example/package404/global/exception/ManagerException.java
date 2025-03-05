package com.example.package404.global.exception;

import com.example.package404.global.response.responseStatus.ManagerResponseStatus;

public class ManagerException extends BaseException {
    public ManagerException(ManagerResponseStatus status) {
        super(status);
    }
}

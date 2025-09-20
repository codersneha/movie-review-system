package com.example.helloWorld.dto.response;

import lombok.Getter;

@Getter
public enum ErrorCode {

    MOVIE_NOT_FOUND_CODE(40400),
    USER_NOT_FOUND(40401),
    REVIEW_NOT_FOUND(40402),
    GENERIC_ERROR_CODE(50000);

    int val;

    ErrorCode(int val) {
        this.val = val;
    }
}

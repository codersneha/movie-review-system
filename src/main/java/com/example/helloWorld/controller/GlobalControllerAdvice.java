package com.example.helloWorld.controller;


import com.example.helloWorld.dto.response.AllErrorResponseWrapper;
import com.example.helloWorld.dto.response.ErrorCode;
import com.example.helloWorld.dto.response.MetaData;
import com.example.helloWorld.dto.response.ResponseWrapperDto;
import com.example.helloWorld.exception.MovieNotFoundException;
import com.example.helloWorld.exception.ReviewNotFoundException;
import com.example.helloWorld.exception.UserNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;
import java.util.UUID;

@ControllerAdvice
public class GlobalControllerAdvice {

    Map<Class<? extends Exception>, ErrorCode> errorCodeMap = Map.of(MovieNotFoundException.class, ErrorCode.MOVIE_NOT_FOUND_CODE,
            UserNotFoundException.class, ErrorCode.USER_NOT_FOUND,
            ReviewNotFoundException.class, ErrorCode.REVIEW_NOT_FOUND);


    @ExceptionHandler({MovieNotFoundException.class, UserNotFoundException.class, ReviewNotFoundException.class})
    public ResponseWrapperDto<Object> handleNotFoundException(Exception exception) {
        ResponseWrapperDto<Object> response = AllErrorResponseWrapper.builder()
                .data(null)
                .metaData(MetaData.builder()
                        .status(404)
                        .requestId(UUID.randomUUID().toString())
                        .errorMessage(exception.getMessage())
                        .errorCode(errorCodeMap.getOrDefault(exception.getClass(), ErrorCode.GENERIC_ERROR_CODE).getVal())
                        .build())
                .build();
        return response;
    }

    @ExceptionHandler({Exception.class})
    public ResponseWrapperDto<Object> handleException(Exception exception) {
        return AllErrorResponseWrapper.builder()
                .data(null)
                .metaData(MetaData.builder()
                        .status(500)
                        .requestId(UUID.randomUUID().toString())
                        .errorMessage(exception.getMessage())
                        .errorCode(errorCodeMap.getOrDefault(exception.getClass(), ErrorCode.GENERIC_ERROR_CODE).getVal())
                        .build())
                .build();
    }


}

package com.example.helloWorld.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public abstract class ResponseWrapperDto<T> {

    private final T data;
    private final MetaData metaData;

}

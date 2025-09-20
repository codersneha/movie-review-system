package com.example.helloWorld.dto.response;

import lombok.Builder;

public class AllErrorResponseWrapper extends ResponseWrapperDto<Object> {
    @Builder
    public AllErrorResponseWrapper(Object data, MetaData metaData) {
        super(data, metaData);
    }
}

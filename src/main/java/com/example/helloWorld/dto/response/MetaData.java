package com.example.helloWorld.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class MetaData {

    private final String requestId;
    private final Integer status;
    private final Integer errorCode;
    private final String errorMessage;
}

package com.example.helloWorld.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddRatingDto {

    int rating;
}
//2025-03-02T22:55:46.805+05:30  WARN 94140 --- [helloWorld] [nio-8081-exec-8] .w.s.m.s.DefaultHandlerExceptionResolver :
// Resolved [org.springframework.http.converter.HttpMessageNotReadableException:
// JSON parse error: Cannot deserialize value of type `int` from Object value (token `JsonToken.START_OBJECT`)]
//2025-03-02T22:57:02.453+05:30  WARN 94140 --- [helloWorld] [nio-8081-exec-9] .w.s.m.s.DefaultHandlerExceptionResolver : Resolved [org.springframework.http.converter.HttpMessageNotReadableException: JSON parse error: Cannot deserialize value of type `int` from Object value (token `JsonToken.START_OBJECT`)]
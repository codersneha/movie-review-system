package com.example.helloWorld.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateMovieRequestDto {

    private final String name; // movie DTO
    private String trailerLink; // movie DTO
    private String posterLink; // Movie DTO
}

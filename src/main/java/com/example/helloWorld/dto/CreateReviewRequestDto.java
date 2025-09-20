package com.example.helloWorld.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateReviewRequestDto {

    final int userId;
    final String description;
    final int rating;
}

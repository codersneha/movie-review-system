package com.example.helloWorld.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateReviewRequestDto {
    final int movieId;
    final int userId;
    final String newDescription;
    final int newRating;
}

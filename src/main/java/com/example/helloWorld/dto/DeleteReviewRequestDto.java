package com.example.helloWorld.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeleteReviewRequestDto {

    final int userId;
    final int movieId;
}

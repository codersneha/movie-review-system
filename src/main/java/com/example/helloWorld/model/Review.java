package com.example.helloWorld.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Review {
    private final int id;
    private final int userId;
    private final String name;
    private final String description;
    private final int rating;
}





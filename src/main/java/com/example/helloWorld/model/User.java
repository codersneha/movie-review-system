package com.example.helloWorld.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class User {
    private final int id;
    private final String userName;
    private final String firstName;
    private final String lastname;
    private final String email;
    private final List<Review> reviews = new ArrayList<>();

    public void addReview(final Review review){
        reviews.add(review);
    }

    public void deleteReview(final Review review){
        reviews.remove(review);
    }

}
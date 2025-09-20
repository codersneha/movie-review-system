package com.example.helloWorld.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * lombok
 * final
 * RequestId
 */
//@data is an lombok annotaion which generates the code for getter, setter, equal, hashcode, to string, required arg constructor
@Data
@Builder
public class Movie {

    private final int id;
    private final String name; // movie DTO

    private String trailerLink; // movie DTO
    private String posterLink; // Movie DTO
    private int totalRating;
    private float avgRating;
    private final List<Review> reviews = new ArrayList<>();
    private final String actorName;


    public void addReview(final Review review) {
        reviews.add(review);
        addRating(review.getRating());
    }

    public void deleteReview(final Review review) {
        reviews.remove(review);
        reduceRating(review.getRating());
    }

    private void reduceRating(final int rating) {
        //avg rating 7, total rating 10
        //remove 9
        if(totalRating > 1) {
            avgRating = (avgRating*totalRating - rating) / (totalRating -1);
        } else {
            avgRating = 0;
        }

        totalRating--;
    }

    public void addRating(final int rating) {
        avgRating = (avgRating*totalRating + rating) / (totalRating +1);
        totalRating ++;
    }

}

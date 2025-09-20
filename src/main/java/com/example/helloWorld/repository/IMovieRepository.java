package com.example.helloWorld.repository;

import com.example.helloWorld.exception.MovieNotFoundException;
import com.example.helloWorld.exception.ReviewNotFoundException;
import com.example.helloWorld.model.Movie;
import com.example.helloWorld.model.Review;
import com.example.helloWorld.model.User;

import java.util.List;

public interface IMovieRepository {

    void save(Movie movie);
    Movie get(int id) throws MovieNotFoundException;
    List<Movie> getAll();
    Movie addReview(int movieId, Review review) throws MovieNotFoundException;
    void deleteReview(int movieId, int reviewId) throws MovieNotFoundException, ReviewNotFoundException;
    Review getReview(int movieId, int reviewId) throws MovieNotFoundException, ReviewNotFoundException;
}
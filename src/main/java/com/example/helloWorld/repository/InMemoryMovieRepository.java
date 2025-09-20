package com.example.helloWorld.repository;

import com.example.helloWorld.exception.MovieNotFoundException;
import com.example.helloWorld.exception.ReviewNotFoundException;
import com.example.helloWorld.model.Movie;
import com.example.helloWorld.model.Review;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class InMemoryMovieRepository implements IMovieRepository{

    private final List<Movie> movieList = new ArrayList<>();

    @Override
    public void save(Movie movie) {
        movieList.add(movie);
        log.info("movie {} created:", movie);
    }

    @Override
    public Movie get(int id) throws MovieNotFoundException {
        for(Movie movie: movieList){
            if(movie.getId() == id) {
                return movie;
            }
        }
        throw new MovieNotFoundException(String.format("Movie with id %s not found", id));
    }

    @Override
    public List<Movie> getAll() {
        return movieList;
    }

    @Override
    public Movie addReview(int movieId, Review review) throws MovieNotFoundException {
        final Movie movie = get(movieId);
        movie.addReview(review);
        return movie;
    }

    @Override
    public void deleteReview(int movieId, int reviewId) throws MovieNotFoundException, ReviewNotFoundException {
        final Movie  movie = get(movieId);
        for (Review review : movie.getReviews()) {
            if (review.getId() == reviewId) {
                    movie.deleteReview(review);
                    return;
            }

        }
        throw new ReviewNotFoundException(String.format("review id %s not found", reviewId));
    }

    @Override
    public Review getReview(int movieId, int reviewId) throws MovieNotFoundException, ReviewNotFoundException {
        final Movie  movie = get(movieId);
        for (Review review : movie.getReviews()) {
            if (review.getId() == reviewId) {
               return  review;
            }
        }
        throw new ReviewNotFoundException(String.format("review id %s not found", reviewId));
    }

}

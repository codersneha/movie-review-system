package com.example.helloWorld.Service;

import com.example.helloWorld.dto.CreateReviewRequestDto;
import com.example.helloWorld.exception.MovieNotFoundException;
import com.example.helloWorld.exception.ReviewNotFoundException;
import com.example.helloWorld.exception.UserNotFoundException;
import com.example.helloWorld.model.Movie;
import com.example.helloWorld.model.Review;
import com.example.helloWorld.model.User;
import com.example.helloWorld.repository.IMovieRepository;
import com.example.helloWorld.repository.IUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewService {

    private static int reviewId=1;
    private final IMovieRepository movieRepository ;
    private final IUserRepository userRepository;

    public Movie addReview(int movieId, CreateReviewRequestDto createReviewRequestDto) throws MovieNotFoundException, UserNotFoundException {


        final User user = userRepository.get(createReviewRequestDto.getUserId());

        final Review review = Review.builder()
                .id(reviewId)
                .name(user.getFirstName()+" "+user.getLastname())
                .userId(createReviewRequestDto.getUserId())
                .description(createReviewRequestDto.getDescription())
                .rating(createReviewRequestDto.getRating())
                .build();
        reviewId++;
       final Movie movie = movieRepository.addReview(movieId, review);
        userRepository.addReview(createReviewRequestDto.getUserId(), review);

        return movie;
    }

    public void deleteReview(int reviewId, int movieId, int userId) throws MovieNotFoundException, UserNotFoundException , ReviewNotFoundException {

        movieRepository.deleteReview(movieId, reviewId);
        userRepository.deleteReview(userId, reviewId);
    }

    public Movie updateReview(int reviewId, int movieId , int userId, String newDescription, Integer newRating) throws ReviewNotFoundException,MovieNotFoundException , UserNotFoundException {

        final Review existingReview = movieRepository.getReview(movieId, reviewId);

        final Review updatedReview = Review.builder()
                .id(existingReview.getId())
                .userId(existingReview.getUserId())
                .description(newDescription == null ? existingReview.getDescription() : newDescription)
                .rating(newRating == null ? existingReview.getRating() : newRating)
                .build();

       movieRepository.deleteReview(movieId,reviewId);
       movieRepository.addReview(movieId,updatedReview);

       userRepository.deleteReview(userId, reviewId);
       userRepository.addReview(userId, updatedReview);
        return movieRepository.get(movieId);
    }


}

package com.example.helloWorld.controller;

import com.example.helloWorld.dto.DeleteReviewRequestDto;
import com.example.helloWorld.Service.ReviewService;
import com.example.helloWorld.dto.CreateReviewRequestDto;
import com.example.helloWorld.dto.UpdateReviewRequestDto;
import com.example.helloWorld.exception.MovieNotFoundException;
import com.example.helloWorld.exception.ReviewNotFoundException;
import com.example.helloWorld.exception.UserNotFoundException;
import com.example.helloWorld.model.Movie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;


    //update this to have a user id
    @PostMapping("/{id}")
    public ResponseEntity<Movie> addReview(@PathVariable("id") final int id, @RequestBody final CreateReviewRequestDto createReviewRequestDto) {
        log.info("Request received to create a review with movie id: {}", id);
        try {
            final Movie movie = reviewService.addReview(id, createReviewRequestDto);
            return ResponseEntity.ok(movie);
        } catch (MovieNotFoundException | UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteReview(@PathVariable("id") final int reviewId, @RequestBody final DeleteReviewRequestDto deleteReviewRequestDto) {
        log.info("Request received to delete a review with review id: {}", reviewId);
        try {
            reviewService.deleteReview(reviewId, deleteReviewRequestDto.getMovieId(), deleteReviewRequestDto.getUserId());
            return ResponseEntity.ok("Successfully deleted review");
        } catch (MovieNotFoundException | UserNotFoundException | ReviewNotFoundException e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PatchMapping("/{id}") //PATCH localhost:8080/reviews/3
    public ResponseEntity<Movie> updateReview(@PathVariable("id") final int reviewId, @RequestBody final UpdateReviewRequestDto updateReviewRequestDto) {
        log.info("Request received to update a review with review id: {}", reviewId);
        try {
            final Movie newMovie = reviewService.updateReview(
                    reviewId, updateReviewRequestDto.getMovieId(), updateReviewRequestDto.getUserId(),
                    updateReviewRequestDto.getNewDescription(), updateReviewRequestDto.getNewRating());
            return ResponseEntity.ok(newMovie);
        } catch (MovieNotFoundException | UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}

package com.example.helloWorld.controller;

import com.example.helloWorld.Service.MovieService;
import com.example.helloWorld.dto.CreateMovieRequestDto;
import com.example.helloWorld.dto.AddRatingDto;
import com.example.helloWorld.dto.response.GetAMovieResponseWrapperDto;
import com.example.helloWorld.dto.response.GetAllMoviesResponseWrapper;
import com.example.helloWorld.dto.response.MetaData;
import com.example.helloWorld.dto.response.ResponseWrapperDto;
import com.example.helloWorld.exception.MovieNotFoundException;
import com.example.helloWorld.model.Movie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 *
 *
 * expand this to support adding rating for a movie
 * - lombok
 * - final
 * - dependency injection
 */

@Slf4j
@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;


    @GetMapping
    public ResponseWrapperDto<List<Movie>> getAllMovies(){
        log.info("Request received to get all movies");
        final List<Movie> movies = movieService.getAllMovies();
        return GetAllMoviesResponseWrapper.builder()
                .data(movies)
                .metaData(MetaData.builder()
                        .requestId(UUID.randomUUID().toString())
                        .errorCode(null)
                        .errorMessage(null)
                        .status(200)
                        .build())
                .build();
    }

    @GetMapping("/{id}")
    public ResponseWrapperDto<Movie> getAMovie(@PathVariable("id") final int id) throws MovieNotFoundException {
        log.info("Request received to get a movie with id: {}", id);

        final Movie movie = movieService.getMovieById(id);
        return GetAMovieResponseWrapperDto.builder()
                .data(movie)
                .metaData(MetaData.builder()
                        .requestId(UUID.randomUUID().toString())
                         .errorCode(null)
                        .errorMessage(null)
                        .status(200)
                        .build())
                .build();

    }

    @PostMapping
    public ResponseEntity<String> createMovie(@RequestBody CreateMovieRequestDto createMovieRequestDto){
        log.info("Request received to create a movie : {}", createMovieRequestDto);
        final Movie movie = movieService.createMovie(createMovieRequestDto);
        return ResponseEntity.ok("Successfully Created movie with id" + movie.getId());
    }

    @PostMapping("/{id}/rate")
    public ResponseEntity<Float> rateMovie(@PathVariable("id")final int id, @RequestBody AddRatingDto addRatingDto){
        log.info("Request received to rate a movie with id: {} with rating {}", id, addRatingDto.getRating());
        try{
            return ResponseEntity.ok(movieService.addRating(id, addRatingDto.getRating()));
        } catch (MovieNotFoundException ex){
            return ResponseEntity.status(404).body(null);
        } catch (Exception ex) {
            return ResponseEntity.status(500).body(null);
        }

    }

    //2xx -> 200: OK, 201: accepted,
    //3xx ->redirected 301, 302
    //4xx -> Client errors
          //-> 400: Bad request
          //401: Unauthenticated
    //403: Forbidden
    //404: Not Found
    //429: Too many requests (Rate Limiting)
    //5xx: Server side errors
        //500:Internal server error//accepting
        //503: Server Unavailable// not even accepting

}

/**
 *
 * {
 *     "data": {
 *
 *     },
 *     "metadata": {
 *         "request_id": ""; -> UUID abcd-1234-uhdrw-acgs
 *         "status": 400,
 *         "errorMessage": null/ "",
 *         "errorCode": 40001
 *     }
 * }
 *
 *
 *
 * {
 *     "data" : ,
 *     "request_id": ""; -> UUID abcd-1234-uhdrw-acgs
 *     "status": 400,
 *     "errorMessage": null/ "",
 *     "errorCode": 40001
 *  }
 *
 * }
 *
 *
 */
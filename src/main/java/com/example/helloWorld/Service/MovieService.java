package com.example.helloWorld.Service;

import com.example.helloWorld.dto.CreateMovieRequestDto;
import com.example.helloWorld.exception.MovieNotFoundException;
import com.example.helloWorld.model.Movie;
import com.example.helloWorld.repository.IMovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MovieService {

    private static int movieId = 1;
    private final IMovieRepository movieRepository;

    //start Assigning incremental id for Movie, hints: use static

    public Movie createMovie(CreateMovieRequestDto createMovieRequestDto){
        final Movie movie = Movie.builder()
                .id(movieId)
                .name(createMovieRequestDto.getName())
                .trailerLink(createMovieRequestDto.getTrailerLink())
                .posterLink(createMovieRequestDto.getPosterLink())
                .build();
        movieRepository.save(movie);
        return movie;
    }

    public List<Movie> getAllMovies(){
        return movieRepository.getAll();
    }

    public Movie getMovieById(int id) throws MovieNotFoundException {
        return movieRepository.get(id);
    }

    public float addRating(int id, int rating) throws MovieNotFoundException {

        final Movie movie = getMovieById(id);
        if(movie== null) {
            throw new MovieNotFoundException(String.format("movie id %s not found", id));
        }
        movie.addRating(rating);
        return movie.getAvgRating();

    }

}

// A movie should have many reviews
//  A review should have
  // - userName
  // - description
  // - rating
// Create a review model, update movie model
// Create a Review Controller which takes request to create a review
// Create review Service which will create the review and update the movie with the review added to the movie
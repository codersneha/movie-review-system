package com.example.helloWorld.dto.response;

import com.example.helloWorld.model.Movie;
import lombok.Builder;


public class GetAMovieResponseWrapperDto extends ResponseWrapperDto<Movie>{

    @Builder
    public GetAMovieResponseWrapperDto(Movie data, MetaData metaData) {
        super(data, metaData);
    }


}

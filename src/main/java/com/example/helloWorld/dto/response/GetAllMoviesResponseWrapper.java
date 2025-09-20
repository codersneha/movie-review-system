package com.example.helloWorld.dto.response;

import com.example.helloWorld.model.Movie;
import lombok.Builder;

import java.util.List;

public class GetAllMoviesResponseWrapper extends ResponseWrapperDto<List<Movie>> {

    @Builder
    GetAllMoviesResponseWrapper(List<Movie> data, MetaData metaData) {
        super(data, metaData);
    }
}

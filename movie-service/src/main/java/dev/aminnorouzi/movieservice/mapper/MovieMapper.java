package dev.aminnorouzi.movieservice.mapper;

import dev.aminnorouzi.movieservice.dto.MovieResponse;
import dev.aminnorouzi.movieservice.model.Movie;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovieMapper {

    private final ModelMapper modelMapper;

    public Movie mapToMovie(MovieResponse response) {
        return modelMapper.map(response, Movie.class);
    }

    public MovieResponse mapFromMovie(Movie movie) {
        return modelMapper.map(movie, MovieResponse.class);
    }

    public MovieResponse.Pageable mapFromMovie(Page<Movie> movies) {
        return modelMapper.map(movies, MovieResponse.Pageable.class);
    }
}

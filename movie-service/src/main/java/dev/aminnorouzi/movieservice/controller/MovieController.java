package dev.aminnorouzi.movieservice.controller;

import dev.aminnorouzi.movieservice.dto.MovieResponse;
import dev.aminnorouzi.movieservice.mapper.MovieMapper;
import dev.aminnorouzi.movieservice.model.Movie;
import dev.aminnorouzi.movieservice.model.Search;
import dev.aminnorouzi.movieservice.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final MovieService movieService;
    private final MovieMapper movieMapper;

    @GetMapping("/{id}")
    public MovieResponse getMovie(@PathVariable Long id) {
        Movie movie = movieService.getById(id);
        return movieMapper.mapFromMovie(movie);
    }

    @PostMapping("/{type}/{id}")
    public MovieResponse getMovieByTmdbId(@PathVariable String type, @PathVariable Long id) {
        Movie movie = movieService.getByTmdbId(id, type);
        return movieMapper.mapFromMovie(movie);
    }

    @GetMapping
    public MovieResponse.Pageable getAllMovies(@RequestParam(defaultValue = "0") Integer page,
                                               @RequestParam(defaultValue = "5") Integer size,
                                               @RequestParam(defaultValue = "id") String sort) {
        Page<Movie> movies = movieService.getAll(page, size, sort);
        return movieMapper.mapFromMovie(movies);
    }

    @GetMapping("/user/{id}")
    public List<MovieResponse> getAllMoviesByUserId(@PathVariable Long id) {
        return movieService.getAllByUserId(id)
                .stream()
                .map(movieMapper::mapFromMovie)
                .toList();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable Long id) {
        movieService.delete(id);
    }

    @PostMapping("/{movieId}/user/{userId}")
    public MovieResponse addMovieToUser(@PathVariable Long movieId, @PathVariable Long userId) {
        Movie movie = movieService.addToUser(movieId, userId);
        return movieMapper.mapFromMovie(movie);
    }

    @GetMapping("/search")
    public List<Search> searchMovies(@RequestParam String query) {
        return movieService.search(query);
    }
}

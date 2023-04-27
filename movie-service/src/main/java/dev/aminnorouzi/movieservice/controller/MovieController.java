package dev.aminnorouzi.movieservice.controller;

import dev.aminnorouzi.movieservice.dto.*;
import dev.aminnorouzi.movieservice.mapper.MetadataMapper;
import dev.aminnorouzi.movieservice.mapper.MovieMapper;
import dev.aminnorouzi.movieservice.model.Metadata;
import dev.aminnorouzi.movieservice.model.Movie;
import dev.aminnorouzi.movieservice.service.MetadataService;
import dev.aminnorouzi.movieservice.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/movies")
public class MovieController {

    private final MovieService movieService;
    private final MovieMapper movieMapper;
    private final MetadataService metadataService;
    private final MetadataMapper metadataMapper;

    // MOVIE ENDPOINTs

    @GetMapping("/{id}")
    public MovieResponse getMovie(@PathVariable Long id) {
        Movie movie = movieService.getById(id);
        return movieMapper.mapFromMovie(movie);
    }

    @PostMapping("/{tmdbId}")
    public MovieResponse getMovieByTmdbId(@PathVariable Long tmdbId, @RequestParam String type) {
        Movie movie = movieService.getByTmdbId(tmdbId, type);
        return movieMapper.mapFromMovie(movie);
    }

    @GetMapping
    public List<MovieResponse> getAllMovies(@RequestParam(defaultValue = "0") Integer page,
                                            @RequestParam(defaultValue = "5") Integer size,
                                            @RequestParam(defaultValue = "id") String sort) {
        return movieService.getAll(page, size, sort)
                .stream()
                .map(movieMapper::mapFromMovie)
                .toList();
    }

    @PutMapping("/{id}")
    public MovieResponse updateMovie(@PathVariable Long id, @RequestBody MovieRequest request) {
        Movie movie = movieService.update(id, request);
        return movieMapper.mapFromMovie(movie);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable Long id) {
        movieService.delete(id);
    }

    // TODO
    @GetMapping("/search/{query}")
    public List<TmdbMovie> searchMovies(@PathVariable String query) {
        return movieService.search(query);
    }

    // METADATA ENDPOINTs - TODO: update endpoints to use metadata id instead of movieId and userId

    @GetMapping("/users/{userId}")
    public List<MovieResponse> getAllMoviesByUserId(@PathVariable Long userId) {
        return movieService.getAllByUserId(userId)
                .stream()
                .map(movieMapper::mapFromMovie)
                .toList();
    }

    @GetMapping("/{movieId}/users/{userId}")
    public MetadataResponse getMetadata(@PathVariable Long movieId, @PathVariable Long userId) {
        Metadata metadata = metadataService.getByMovieIdAndUserId(movieId, userId);
        return metadataMapper.mapFromMetadata(metadata);
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public MetadataResponse addMetadata(@RequestBody MetadataRequest request) {
        Metadata metadata = metadataService.add(request);
        return metadataMapper.mapFromMetadata(metadata);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{movieId}/users/{userId}")
    public void deleteMetadata(@PathVariable Long movieId, @PathVariable Long userId) {
        metadataService.delete(movieId, userId);
    }

    @PutMapping("/{movieId}/users/{userId}")
    public MetadataResponse watchOrUnwatchMetadata(@PathVariable Long movieId, @PathVariable Long userId,
                                                   @RequestParam Boolean watch) {
        Metadata metadata;
        if (watch) {
            metadata = metadataService.watch(movieId, userId);
        } else {
            metadata = metadataService.unwatch(movieId, userId);
        }

        return metadataMapper.mapFromMetadata(metadata);
    }

    /* MOVIES
     * - get all movies /movies (has default pagination) GET
     * - get a movie /movies/({movieId}/{tmdbId})?type={type} (not always required) POST/GET
     * - delete a movie /movies/{movieId} DELETE
     * - update a movie /movies/{movieId} [MovieRequest] POST
     * - search a query /movies/{query} GET
     */
    /* METADATA
     * - get all user movies /movies/users/{userId} GET
     * - delete a user movie /movies/{movieId}/users/{userId} DELETE
     * - watch a user movie /movies/{movieId}/users/{userId}?watch=true POST
     * - unwatch a user movie /movies/{movieId}/users/{userId}?watch=true POST
     * - add a user movie /movies/users [UserMovieRequest] POST
     */
    /* USERS
        - get all user movies /users/{userId}/movies GET
        - get a user movie /users/{userId}/movies/{movieId} GET
        - delete a user movie /users/{userId}/movies/{movieId} DELETE
        - watch a user movie /users/{userId}/movies/{movieId}?watch=true POST
        - unwatch a user movie /users/{userId}/movies/{movieId}?watch=false POST
        - add a user movie /users/movies POST [UserMovieRequest] POST
     */
}

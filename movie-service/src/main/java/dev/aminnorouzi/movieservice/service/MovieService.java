package dev.aminnorouzi.movieservice.service;

import dev.aminnorouzi.movieservice.client.MovieClient;
import dev.aminnorouzi.movieservice.dto.MovieRequest;
import dev.aminnorouzi.movieservice.exception.MovieNotFoundException;
import dev.aminnorouzi.movieservice.model.Movie;
import dev.aminnorouzi.movieservice.model.Type;
import dev.aminnorouzi.movieservice.repository.MovieRepository;
import dev.aminnorouzi.movieservice.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieClient movieClient;
    private final StringUtil stringUtil;

    public Movie save(MovieRequest request) {
        String generatedImdbUrl = stringUtil.generateImdbUrl(request.getImdbId());

        Movie movie = Movie.builder()
                .tmdbId(request.getTmdbId())
                .imdbId(request.getImdbId())
                .title(request.getTitle())
                .overview(request.getOverview())
                .poster(request.getPoster())
                .backdrop(request.getBackdrop())
                .runtime(request.getRuntime())
                .website(generatedImdbUrl)
                .type(Type.of(request.getType()))
                .rating(request.getRating())
                .episodes(request.getEpisodes())
                .seasons(request.getSeasons())
//                .genres()
                .released(request.getReleased())
                .build();

        Movie saved = movieRepository.save(movie);

        log.info("Saved new movie: {}", saved);
        return saved;
    }

    public Movie getByTmdbId(Long tmdbId, String type) {
        Movie movie;

        Optional<Movie> optional = movieRepository.findByTmdbId(tmdbId);
        if (optional.isPresent()) {
            movie = optional.get();
        } else {
            MovieRequest request = movieClient.get(tmdbId, type);
            request.setType(type);

            movie = save(request);
        }

        log.info("Found new movie: {}", movie);
        return movie;
    }

    public Movie getById(Long id) {
        Movie found = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie: %s does not exist".formatted(id)));

        log.info("Found a movie: {}", found);
        return found;
    }

    public Page<Movie> getAll(Integer page, Integer size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

        Page<Movie> found = movieRepository.findAll(pageable);

        log.info("Found all movies: filter={}, {}", pageable, found);
        return found;
    }

    public void delete(Long id) {
        Movie movie = getById(id);
        movieRepository.delete(movie);

        log.info("Deleted a movie: {}", movie);
    }
}

package dev.aminnorouzi.movieservice.service;

import dev.aminnorouzi.movieservice.client.MovieClient;
import dev.aminnorouzi.movieservice.dto.MovieRequest;
import dev.aminnorouzi.movieservice.dto.TmdbMovie;
import dev.aminnorouzi.movieservice.exception.MovieNotFoundException;
import dev.aminnorouzi.movieservice.model.Metadata;
import dev.aminnorouzi.movieservice.model.Movie;
import dev.aminnorouzi.movieservice.model.Type;
import dev.aminnorouzi.movieservice.repository.MovieRepository;
import dev.aminnorouzi.movieservice.util.BeanUtil;
import dev.aminnorouzi.movieservice.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieClient movieClient;
    private final StringUtil stringUtil;
    private final MetadataService metaDataService;
    private final SearchService searchService;
    private final BeanUtil beanUtil;

    public Movie save(TmdbMovie request) {
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
            TmdbMovie request = movieClient.get(tmdbId, type);
            request.setType(type);

            movie = save(request);
        }

        log.info("Found a movie: {}", movie);
        return movie;
    }

    public Movie getById(Long id) {
        Movie found = movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie: %s does not exist".formatted(id)));

        log.info("Found a movie: {}", found);
        return found;
    }

    public List<Movie> getAll(Integer page, Integer size, String sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

        Page<Movie> found = movieRepository.findAll(pageable);

        log.info("Found all movies: filter={}, {}", pageable, found.getContent());
        return found.getContent();
    }

    public List<Movie> getAllByUserId(Long userId) {
        List<Movie> found = new ArrayList<>();

        List<Metadata> metadataList = metaDataService.getAllByUserId(userId);
        metadataList.forEach(metadata -> {
            Movie movie = getById(metadata.getMovieId());
            movie.setMetadata(metadata);

            found.add(movie);
        });

        log.info("Found all user movies: userId={}, {}", userId, found);
        return found;
    }

    @Transactional
    public Movie update(Long id, MovieRequest request) {
        Movie movie = getById(id);

        if (!beanUtil.hasDifference(movie, request)) {
            throw new IllegalArgumentException("No difference found between request and movie!");
        }

        beanUtil.copy(request, movie);
        Movie updated = movieRepository.save(movie);

        log.info("Updated a movie: {}", updated);
        return updated;
    }

    public void delete(Long id) {
        Movie movie = getById(id);
        movieRepository.delete(movie);

        log.info("Deleted a movie: {}", movie);
    }

    public List<TmdbMovie> search(String query) {
        return searchService.search(query);
    }
}

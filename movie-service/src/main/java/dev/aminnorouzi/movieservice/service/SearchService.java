package dev.aminnorouzi.movieservice.service;

import dev.aminnorouzi.movieservice.client.MovieClient;
import dev.aminnorouzi.movieservice.exception.MovieNotFoundException;
import dev.aminnorouzi.movieservice.model.Search;
import dev.aminnorouzi.movieservice.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SearchService {

    private final MovieClient movieClient;
    private final StringUtil stringUtil;

    public List<Search> search(String query) {
        List<Search> result = findByImdbIdOrQuery(query).stream()
                .filter(this::filter)
                .toList();

        verify(result);

        log.info("Searched a query: query={}, result={}", query, result);
        return result;
    }

    private List<Search> findByImdbIdOrQuery(String query) {
        List<Search> response;
        if (stringUtil.containsImdbId(query)) {
            response = findByImdbId(query);
        } else {
            response = findByQuery(query);
        }
        return response;
    }

    private List<Search> findByImdbId(String query) {
        List<Search> response;
        String imdbId = stringUtil.extractImdbId(query);
        Search.Response found = movieClient.find(imdbId);

        if (!found.getMovies().isEmpty()) {
            response = found.getMovies();
        } else {
            response = found.getTvs();
        }
        return response;
    }

    private List<Search> findByQuery(String query) {
        return movieClient.search(query).getResults();
    }

    private boolean filter(Search search) {
        return search.getPoster() != null &&
                search.getBackdrop() != null &&
                search.getOverview() != null &&
                search.getReleased() != null;
    }

    private void verify(List<Search> result) {
        if (result.isEmpty()) {
            throw new MovieNotFoundException("Movie does not exist.");
        }
    }
}

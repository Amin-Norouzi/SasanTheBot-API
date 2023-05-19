package dev.aminnorouzi.movieservice.client;

import dev.aminnorouzi.movieservice.model.Search;
import dev.aminnorouzi.movieservice.model.TmdbMovie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "MovieClient", url = "${movie.client.api-url}")
public interface MovieClient {

    @GetMapping("${movie.client.api.get-search}")
    Search.Response search(@PathVariable String query);

    @GetMapping("${movie.client.api.get-find}")
    Search.Response find(@PathVariable String imdbId);

    @GetMapping("${movie.client.api.get-movie}")
    TmdbMovie get(@PathVariable Long tmdbId, @PathVariable String type);

    @GetMapping("${movie.client.api.get-trending}")
    Search.Response trending();
}

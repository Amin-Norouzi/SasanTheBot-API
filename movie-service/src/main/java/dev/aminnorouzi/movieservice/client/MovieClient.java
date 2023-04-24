package dev.aminnorouzi.movieservice.client;

import dev.aminnorouzi.movieservice.dto.MovieRequest;
import dev.aminnorouzi.movieservice.model.Search.SearchResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "MovieClient", url = "${movie.client.api-url}")
public interface MovieClient {

    @GetMapping("${movie.client.api.get-search}")
    SearchResponse search(@PathVariable String query);

    @GetMapping("${movie.client.api.get-find}")
    SearchResponse find(@PathVariable String imdbId);

    @GetMapping("${movie.client.api.get-movie}")
    MovieRequest get(@PathVariable Long tmdbId, @PathVariable String type);

    @GetMapping("${movie.client.api.get-trending}")
    SearchResponse trending();
}

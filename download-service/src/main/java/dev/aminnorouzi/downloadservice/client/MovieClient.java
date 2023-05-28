package dev.aminnorouzi.downloadservice.client;

import dev.aminnorouzi.downloadservice.model.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "movie-service")
public interface MovieClient {

    @GetMapping("${movie.client.get}")
    Movie get(@PathVariable Long id);
}

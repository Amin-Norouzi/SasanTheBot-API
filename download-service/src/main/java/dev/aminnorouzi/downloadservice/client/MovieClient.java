package dev.aminnorouzi.downloadservice.client;

import dev.aminnorouzi.downloadservice.model.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "movie-service", url = "http://localhost:9092")
public interface MovieClient {

    @GetMapping("/api/v1/movies/{id}")
    Movie get(@PathVariable Long id);
}

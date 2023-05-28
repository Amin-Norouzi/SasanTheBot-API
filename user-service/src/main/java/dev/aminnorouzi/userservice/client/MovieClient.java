package dev.aminnorouzi.userservice.client;

import dev.aminnorouzi.userservice.model.movie.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "movie-service")
public interface MovieClient {

    @GetMapping("${movie.client.get-by-user-id}")
    List<Movie> getAllByUserId(@PathVariable Long userId);
}

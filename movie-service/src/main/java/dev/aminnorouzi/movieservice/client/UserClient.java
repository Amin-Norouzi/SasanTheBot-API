package dev.aminnorouzi.movieservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "UserClient", url = "${movie.client.api-url}")
public interface UserClient {

    @GetMapping("{userId}/verify")
    void verify(@PathVariable Long userId);
}

package dev.aminnorouzi.downloadservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient
public interface MovieClient {

    @GetMapping("/api/v1/movies/{id}")
    Object get(@PathVariable Long id);
}

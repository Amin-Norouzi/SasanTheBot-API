package dev.aminnorouzi.movieservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "UserClient", url = "http://localhost:8763/api/v1/users")
public interface UserClient {

    @GetMapping("/{id}/verify")
    boolean verify(@PathVariable Long id);
}

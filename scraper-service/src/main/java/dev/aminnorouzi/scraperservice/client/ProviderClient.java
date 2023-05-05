package dev.aminnorouzi.scraperservice.client;

import dev.aminnorouzi.scraperservice.model.Provider;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "provider-service", url = "http://localhost:9093")
public interface ProviderClient {

    @GetMapping("/api/v1/providers/{id}")
    Provider get(@PathVariable Long id);
}

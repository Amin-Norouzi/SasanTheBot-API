package dev.aminnorouzi.downloadservice.client;

import dev.aminnorouzi.downloadservice.model.Provider;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "provider-service", url = "http://localhost:9093")
public interface ProviderClient {

    @GetMapping("/api/v1/providers/available")
    List<Provider> getAvailable();
}

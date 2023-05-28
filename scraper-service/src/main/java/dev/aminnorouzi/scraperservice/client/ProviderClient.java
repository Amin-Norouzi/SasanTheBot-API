package dev.aminnorouzi.scraperservice.client;

import dev.aminnorouzi.scraperservice.model.Provider;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "provider-service")
public interface ProviderClient {

    @GetMapping("${provider.client.get}")
    Provider get(@PathVariable Long id);
}

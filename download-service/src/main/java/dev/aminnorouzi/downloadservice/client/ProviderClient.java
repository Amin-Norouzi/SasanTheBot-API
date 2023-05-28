package dev.aminnorouzi.downloadservice.client;

import dev.aminnorouzi.downloadservice.model.Provider;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "provider-service")
public interface ProviderClient {

    @GetMapping("${provider.client.get-available}")
    List<Provider> getAvailable();
}

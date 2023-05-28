package dev.aminnorouzi.downloadservice.client;

import dev.aminnorouzi.downloadservice.model.Scraped;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "scraper-service")
public interface ScraperClient {

    @GetMapping("${scraper.client.scrape-by-id}")
    Scraped scrape(@PathVariable Integer providerId, @PathVariable String imdbId);
}

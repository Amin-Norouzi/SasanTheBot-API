package dev.aminnorouzi.downloadservice.client;

import dev.aminnorouzi.downloadservice.model.Scraped;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "scraper-service", url = "http://localhost:9093")
public interface ScraperClient {

    @GetMapping("/api/v1/scrapes/{providerId}/{imdbId}")
    Scraped scrape(@PathVariable Integer providerId, @PathVariable String imdbId);
}

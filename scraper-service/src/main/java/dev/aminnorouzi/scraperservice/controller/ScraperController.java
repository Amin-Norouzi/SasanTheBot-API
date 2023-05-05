package dev.aminnorouzi.scraperservice.controller;

import dev.aminnorouzi.scraperservice.model.Scraped;
import dev.aminnorouzi.scraperservice.service.ScraperService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/scrapers")
public class ScraperController {

    private final ScraperService scraperService;

    @GetMapping("/{providerId}/{imdbId}")
    public Scraped scrape(@PathVariable Long providerId, @PathVariable String imdbId) {
        return scraperService.scrape(providerId, imdbId);
    }

    @GetMapping("/{imdbId}")
    public List<Scraped> scrapeAll(@PathVariable String imdbId) {
        return scraperService.scrape(imdbId);
    }
}

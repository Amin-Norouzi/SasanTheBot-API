package dev.aminnorouzi.scraperservice.service;

import dev.aminnorouzi.scraperservice.client.ProviderClient;
import dev.aminnorouzi.scraperservice.core.Scraper;
import dev.aminnorouzi.scraperservice.core.impl.AvamovieScraper;
import dev.aminnorouzi.scraperservice.core.impl.FilmbanScraper;
import dev.aminnorouzi.scraperservice.model.Link;
import dev.aminnorouzi.scraperservice.model.Scraped;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScraperService {

    //    private final Scraper scraper;
    private final FilmbanScraper filmbanScraper;
    private final AvamovieScraper avamovieScraper;
    private final ProviderClient providerClient;

    // we should initialize scrapers with tasks like once a day from provider service
    public Scraped scrape(Long providerId, String imdbId) {
//        Provider provider = providerClient.get(providerId);

        Scraper scraper = instance(providerId);

        Scraped scraped = new Scraped();
        scraped.setProviderId(providerId);

        String url = scraper.find(imdbId);
        if (url == null) {
            scraped.setSucceed(false);
        }
        List<Link> links = scraper.run(url);
        if (links.isEmpty()) {
            scraped.setSucceed(false);
            scraped.setLinks(links);
        }

        scraped.setUrl(url);
        scraped.setSucceed(true);
        scraped.setLinks(links);
        scraped.setScrapedAt(LocalDateTime.now());

        log.info("Scrape new movie: {}", scraped);
        return scraped;
    }

    // TODO hard coded ids
    public List<Scraped> scrape(String imdbId) {
        List<Scraped> result = new ArrayList<>();
        result.add(scrape(1L, imdbId));
        result.add(scrape(2L, imdbId));

        return result;
    }

    private Scraper instance(Long id) {
        if (id == 1L) return avamovieScraper;
        if (id == 2L) return filmbanScraper;
        throw new IllegalArgumentException();
    }
}
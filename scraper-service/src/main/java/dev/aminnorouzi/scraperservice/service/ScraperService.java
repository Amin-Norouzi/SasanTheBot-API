package dev.aminnorouzi.scraperservice.service;

import dev.aminnorouzi.scraperservice.client.ProviderClient;
import dev.aminnorouzi.scraperservice.core.Scraper;
import dev.aminnorouzi.scraperservice.core.impl.AvamovieScraper;
import dev.aminnorouzi.scraperservice.core.impl.FilmbanScraper;
import dev.aminnorouzi.scraperservice.exception.ScraperNotFoundException;
import dev.aminnorouzi.scraperservice.model.Link;
import dev.aminnorouzi.scraperservice.model.Provider;
import dev.aminnorouzi.scraperservice.model.Scraped;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScraperService {

//    private final List<Scraper> scrapers;
    private final ProviderClient providerClient;

    private final Map<Long, Provider> providers;
    private final AvamovieScraper avamovieScraper;
    private final FilmbanScraper filmbanScraper;

    public Scraped scrape(Long providerId, String imdbId) {
//        Scraper scraper = scrapers.stream()
//                .filter(s -> Long.valueOf(s.getProvider().getId()).equals(providerId))
//                .findFirst()
//                .get();

        Scraper scraper;
        if (providerId == 1L) {
            scraper = avamovieScraper;
            scraper.setProvider(providers.get(providerId));
        } else {
            scraper = filmbanScraper;
            scraper.setProvider(providers.get(providerId));
        }

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
}

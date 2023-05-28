package dev.aminnorouzi.scraperservice.config;

import dev.aminnorouzi.scraperservice.client.ProviderClient;
import dev.aminnorouzi.scraperservice.core.Scraper;
import dev.aminnorouzi.scraperservice.core.impl.AvamovieScraper;
import dev.aminnorouzi.scraperservice.core.impl.FilmbanScraper;
import dev.aminnorouzi.scraperservice.model.Provider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class ScraperConfig {

    private final AvamovieScraper avamovieScraper;
    private final FilmbanScraper filmbanScraper;

//    @Bean
//    public List<Scraper> scrapers(ProviderClient client) {
//        Provider p1 = client.get(1L);
//        avamovieScraper.setProvider(p1);
//
//        Provider p2 = client.get(2L);
//        filmbanScraper.setProvider(p2);
//
//        return List.of(avamovieScraper, filmbanScraper);
//    }

    @Bean
    public Map<Long, Provider> providers(ProviderClient client) {
        Provider p1 = client.get(1L);
        Provider p2 = client.get(2L);

        return Map.of(1L, p1, 2L, p2);
    }
}

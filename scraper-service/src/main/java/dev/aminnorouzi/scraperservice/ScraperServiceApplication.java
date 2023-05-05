package dev.aminnorouzi.scraperservice;

import dev.aminnorouzi.scraperservice.client.ProviderClient;
import dev.aminnorouzi.scraperservice.core.impl.AvamovieScraper;
import dev.aminnorouzi.scraperservice.core.impl.FilmbanScraper;
import dev.aminnorouzi.scraperservice.model.Provider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class ScraperServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScraperServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ProviderClient client, AvamovieScraper avamovieScraper, FilmbanScraper filmbanScraper) {
        return args -> {
            Provider p1 = client.get(1L);
            avamovieScraper.initialize(p1);

            Provider p2 = client.get(2L);
            filmbanScraper.initialize(p2);
        };
    }
}

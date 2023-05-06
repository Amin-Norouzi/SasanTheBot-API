package dev.aminnorouzi.scraperservice.core;

import dev.aminnorouzi.scraperservice.model.Link;
import dev.aminnorouzi.scraperservice.model.Provider;
import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Data
@Component
public abstract class Scraper {

    private Provider provider;

    // returns the url of movie in given provider
    public abstract String find(String imdbId);

    public abstract List<Link> run(String url);

    public Document connect(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

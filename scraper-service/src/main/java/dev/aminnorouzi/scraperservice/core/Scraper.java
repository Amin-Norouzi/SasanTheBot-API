package dev.aminnorouzi.scraperservice.core;

import dev.aminnorouzi.scraperservice.model.Link;
import dev.aminnorouzi.scraperservice.model.Provider;
import lombok.Data;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public abstract class Scraper {

    private Integer id;
    private String url;
    private String query;

    public void initialize(Provider provider) {
        this.id = provider.getId();
        this.url = provider.getUrl();
        this.query = provider.getQuery();
    }

    // returns the url of movie in given provider
    public abstract String find(String imdbId);

    public abstract List<Link> run(String url);

    public abstract Document connect(String url);
}

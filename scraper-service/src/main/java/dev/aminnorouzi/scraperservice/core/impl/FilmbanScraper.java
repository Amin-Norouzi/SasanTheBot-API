package dev.aminnorouzi.scraperservice.core.impl;

import dev.aminnorouzi.scraperservice.core.Scraper;
import dev.aminnorouzi.scraperservice.model.Link;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class FilmbanScraper extends Scraper {

    @Override
    public String find(String imdbId) {
        Document doc = connect("https://filmban.click/?s=tt0068646");

        String href = doc.select(".MainWrapper article")
                .first()
                .select(".headerArticle a")
                .attr("href");

        if (href.isBlank()) {
            return null;
        }

        return href;
    }

    @Override
    public List<Link> run(String url) {
        Document doc = connect(url);

        List<Link> links = new ArrayList<>();

        for (Element element : doc.select(".DownloadLinks p")) {
            String text = element.text();

            if (text.contains("p")) {
                List<String> strings = List.of(text.split("/"));

                String quality = strings.get(0).trim() + " "
                        + strings.get(1).trim();
//                        + " "
//                        + strings.get(2).trim();
                String size = strings.get(strings.size() - 1).trim();

                Link newLink = new Link();
                newLink.setQuality(quality);
                newLink.setSize(size);

                links.add(newLink);
            }
            if (element.select("a").text().contains("دانلود")) {
                String href = element.select("a").attr("href");
                links.get(links.size() - 1).setUrl(href);
            }
        }

        return links;
    }

    @Override
    public Document connect(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

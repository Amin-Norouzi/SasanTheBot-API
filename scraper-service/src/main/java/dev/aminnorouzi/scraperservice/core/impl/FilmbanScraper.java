package dev.aminnorouzi.scraperservice.core.impl;

import dev.aminnorouzi.scraperservice.core.Scraper;
import dev.aminnorouzi.scraperservice.model.Link;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FilmbanScraper extends Scraper {

    @Override
    public String find(String imdbId) {
        try {
            Document doc = connect(getProvider().getUrl() + getProvider().getQuery());

            String href = doc.select(".MainWrapper article")
                    .first()
                    .select(".headerArticle a")
                    .attr("href");

            if (href.isBlank()) {
                return null;
            }

            return href;
        } catch (RuntimeException exception) {
            System.err.println(exception.getMessage());
            return null;
        }
    }

    @Override
    public List<Link> run(String url) {
        List<Link> links = new ArrayList<>();

        try {
            Document doc = connect(url);

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
        } catch (RuntimeException exception) {
            System.err.println(exception.getMessage());
        }

        return links;
    }
}

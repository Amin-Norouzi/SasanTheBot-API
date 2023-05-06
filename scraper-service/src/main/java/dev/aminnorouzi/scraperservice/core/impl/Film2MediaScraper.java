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
public class Film2MediaScraper extends Scraper {

    @Override
    public String find(String imdbId) {
        Document doc = connect("https://www.f2m.kim/?controller=search-movie&s=One Fine Morning");

        return doc.select("article.movie").first()
                .select(".m-title a")
                .attr("href");
    }

    @Override
    public List<Link> run(String url) {
        Document doc = connect(url);

        List<Link> links = new ArrayList<>();

        int count = 0;
        for (Element element : doc.select(".m_content > p")) {
            String text = element.text();

            if (text.equals("&nbsp;")) {
                count++;
            }
            if (count == 2) break;

            if (text.contains("p")) {
                Link link = new Link();
                link.setQuality(text);

                links.add(link);
            }
            if (text.contains("مستقیم") || text.contains("لينک") || text.contains("لينک مستقيم")) {
                String href = element.select("a").attr("href");

                links.get(links.size() - 1)
                        .setUrl(href);
            }
        }

        return links;
    }
}

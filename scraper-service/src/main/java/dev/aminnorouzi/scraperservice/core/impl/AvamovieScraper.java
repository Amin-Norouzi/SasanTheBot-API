package dev.aminnorouzi.scraperservice.core.impl;

import dev.aminnorouzi.scraperservice.core.Scraper;
import dev.aminnorouzi.scraperservice.model.Link;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AvamovieScraper extends Scraper {

    private final String downloadRowHeaderName = ".siteSingle__boxContent__downloadHeader.s-abasi";
    private final String downloadRowBodyName = ".collapse-content.siteSingle__boxContent__downloadContent.abasi-dlbox";

    @Override
    public String find(String imdbId) {
        try {
            Document doc = connect(getProvider().getUrl() + getProvider().getQuery() + imdbId);

            String href = doc.select(".col.col-70.home_loop_post article")
                    .first()
                    .select(".title a")
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

            for (Element element : doc.select(".siteSingle__boxContent div")) {

                if (element.select(downloadRowHeaderName + " em").text().equals("زیرنویس")) {
                    for (Element item : element.select(downloadRowBodyName + " .item")) {
                        Elements data = item.select(".row_data");

                        // check if the download needs vip plan or not
                        String vip = data.select(".need_vip_plan").text();
                        if (!vip.isBlank()) {
                            return links;
                        }

                        String href = data.select(".right-area")
                                .select("a")
                                .attr("href");

                        Elements info = data.select(".left-area");

                        String span = info.select(".quality span").text().trim();
                        String quality = info.select(".quality").text().trim()
                                .replace(span, "").trim();

                        String size = info.select(".details .size").text().trim()
                                .replace("حجم:", "").trim();

                        Link newLink = new Link();
                        newLink.setUrl(href);
                        newLink.setQuality(quality);
                        newLink.setSize(size);

                        links.add(newLink);
                    }
                }
            }
        } catch (RuntimeException exception) {
            System.err.println(exception.getMessage());
        }

        return links;
    }
}

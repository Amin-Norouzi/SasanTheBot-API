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
public class MyHastidlScraper extends Scraper {

    @Override
    public String find(String imdbId) {
        Document doc = connect("http://myhastidl1.cam/?s=tt15326988");

        for (Element element : doc.select(".main-sidebar.col-md-6.col-xs-12 article")) {
            if (element.select(".head a").text().contains("دوبله")) continue;
            return element.select(".head a").attr("href");
        }

        return null;
    }

    @Override
    public List<Link> run(String url) {
        String providerBaseUrl = "http://myhastidl1.cam";

        Document doc = connect(url);

        List<Link> links = new ArrayList<>();

        int count = 0;
        for (Element element : doc.select(".main-content p")) {
            String text = element.text();

            if (text.contains("=")) {
                count++;
            }
            if (count == 0) continue;
            if (count == 2) break;

            for (Element span : element.select("p span")) {
                String spanText = span.text();
                if (spanText.contains("کیفیت")) {
                    String quality = spanText.replace("کیفیت", "").trim();

                    Link newLink = new Link();
                    newLink.setQuality(quality);
                    links.add(newLink);
                }
                if (span.select("a").text().contains("دانلود")) {
                    String href = span.select("a").attr("href");
                    links.get(links.size() - 1).setUrl(providerBaseUrl + href);
                }
                if (spanText.contains("مگابایت") || spanText.contains("گیگابایت")) {
                    String size = spanText.replace("مگابایت", "")
                            .replace("گیگابایت", "").trim();
                    links.get(links.size() - 1).setSize(size);
                }
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

    public static void main(String[] args) {
        Scraper s = new MyHastidlScraper();
        String url = s.find("");
        System.out.println(url);
        System.out.println();

        if (url != null && !url.isBlank()) {
            List<Link> links = s.run(url);
            links.forEach(l -> {
                System.out.println(l.getQuality());
                System.out.println(l.getUrl());
                System.out.println(l.getSize());
            });
        } else {
            System.out.println("Invalid url!");
        }
    }
}

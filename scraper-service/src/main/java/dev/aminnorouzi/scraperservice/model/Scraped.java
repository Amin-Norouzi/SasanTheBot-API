package dev.aminnorouzi.scraperservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Scraped {

    private Long providerId;
    private String url;
    private Boolean succeed;
    private List<Link> links;
    private LocalDateTime scrapedAt;
}

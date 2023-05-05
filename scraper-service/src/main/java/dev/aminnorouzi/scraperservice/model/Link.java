package dev.aminnorouzi.scraperservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Link {

    private String url;
    private String quality;
    private String size; // ? null or int
    private String season; // ? null or int
    private String episode; // ? null or int
}

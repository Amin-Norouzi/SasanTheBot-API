package dev.aminnorouzi.downloadservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Provider {

    private Integer id;
    private String title;
    private String url;
    private String query;
    private String phrase;
    private Boolean isBanned;
    private Boolean isAvailable;
}



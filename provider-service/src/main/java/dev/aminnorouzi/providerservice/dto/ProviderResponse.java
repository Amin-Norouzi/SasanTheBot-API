package dev.aminnorouzi.providerservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProviderResponse {

    private Integer id;
    private String title;
    private String url;
    private String phrase;
    private Boolean isBanned;
    private Boolean isAvailable;
}

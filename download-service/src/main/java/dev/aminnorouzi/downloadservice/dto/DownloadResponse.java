package dev.aminnorouzi.downloadservice.dto;

import dev.aminnorouzi.downloadservice.model.Link;
import dev.aminnorouzi.downloadservice.model.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DownloadResponse {

    private Long id;
    private Long movieId;
    private Long providerId;
    private String url;
    private Type type;
    private List<Link> links;
    private DownloadedResponse downloaded;
}

package dev.aminnorouzi.downloadservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DownloadRequest {

    private Long userId;
    private Long movieId;
    private String imdbId;
}

package dev.aminnorouzi.downloadservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DownloadedRequest {

    private Long downloadId;
    private Long userId;
}

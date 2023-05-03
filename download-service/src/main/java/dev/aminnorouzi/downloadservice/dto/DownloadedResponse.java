package dev.aminnorouzi.downloadservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DownloadedResponse {

    private Long id;
    private Long userId;
    private LocalDateTime createdAt;
}

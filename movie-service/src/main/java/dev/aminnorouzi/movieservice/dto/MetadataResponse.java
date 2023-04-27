package dev.aminnorouzi.movieservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetadataResponse {

    private Long id;
    private LocalDateTime watchedAt;
    private LocalDateTime createdAt;
}

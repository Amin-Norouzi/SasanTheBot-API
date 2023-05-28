package dev.aminnorouzi.userservice.model.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Metadata {

    private Long id;
    private LocalDateTime watchedAt;
    private LocalDateTime createdAt;
}

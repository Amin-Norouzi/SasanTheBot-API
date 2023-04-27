package dev.aminnorouzi.movieservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MetadataRequest {

    @NotNull
    private Long movieId;

    @NotNull
    private Long userId;
}

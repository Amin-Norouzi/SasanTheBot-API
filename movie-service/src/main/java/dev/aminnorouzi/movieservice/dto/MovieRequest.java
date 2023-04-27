package dev.aminnorouzi.movieservice.dto;

import dev.aminnorouzi.movieservice.model.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.URL;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieRequest {

    @NotNull
    private Long tmdbId;

    @NotNull
    @NotBlank
    @Pattern(regexp = "(tt\\d+)")
    private String imdbId;

    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @NotBlank
    @ToString.Exclude
    private String overview;

    @URL
    @NotNull
    @NotBlank
    private String poster;

    @URL
    @NotNull
    @NotBlank
    private String backdrop;

    @NotNull
    @NotBlank
    private String runtime;

    @URL
    @NotNull
    @NotBlank
    private String website;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Type type;

    @NotNull
    @PositiveOrZero
    private Double rating;

    @NotNull
    @PositiveOrZero
    private Integer episodes;

    @NotNull
    @PositiveOrZero
    private Integer seasons;

    @NotNull
    private LocalDate released;
}

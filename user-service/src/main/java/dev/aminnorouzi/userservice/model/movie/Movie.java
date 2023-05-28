package dev.aminnorouzi.userservice.model.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    private Long id;
    private Long tmdbId;
    private String imdbId;
    private String title;
    private String overview;
    private String poster;
    private String backdrop;
    private String runtime;
    private String website;
    private Type type;
    private Double rating;
    private Integer episodes;
    private Integer seasons;
    private LocalDate released;
    private List<Genre> genres;
    private Metadata metadata;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Pageable {

        private List<Movie> content;
        private Integer numberOfElements;
        private Integer totalPages;
        private Integer totalElements;
    }
}

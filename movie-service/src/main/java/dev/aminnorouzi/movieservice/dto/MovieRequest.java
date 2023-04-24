package dev.aminnorouzi.movieservice.dto;

import com.fasterxml.jackson.annotation.*;
import dev.aminnorouzi.movieservice.annotation.CalculatedTime;
import dev.aminnorouzi.movieservice.annotation.FullPathUrl;
import dev.aminnorouzi.movieservice.annotation.ImdbFromJson;
import dev.aminnorouzi.movieservice.annotation.SimpleDoubleNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieRequest {

    @JsonProperty("id")
    private Long tmdbId;

    @ImdbFromJson
    @JsonProperty("external_ids")
    private String imdbId;

    @JsonAlias({"title", "name"})
    private String title;

    @JsonProperty("overview")
    private String overview;

    @FullPathUrl
    @JsonProperty("poster_path")
    private String poster;

    @FullPathUrl
    @JsonProperty("backdrop_path")
    private String backdrop;

    @CalculatedTime
    @JsonAlias({"runtime", "episode_run_time"})
    private String runtime = "0";

    @SimpleDoubleNumber
    @JsonProperty("vote_average")
    private Double rating;

    @JsonProperty(value = "number_of_episodes")
    private Integer episodes = 1;

    @JsonProperty(value = "number_of_seasons")
    private Integer seasons = 0;

    @JsonAlias({"release_date", "first_air_date"})
    private LocalDate released;

    @JsonIgnore
    private String type;

//    @CollectionOfJson
//    @JsonProperty("genres")
//    @Column(name = "genre")
//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "movie_genres", joinColumns = @JoinColumn(name = "movie_id", referencedColumnName = "id"))
//    private List<String> genres;
}

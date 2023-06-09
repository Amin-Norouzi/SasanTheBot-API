package dev.aminnorouzi.movieservice.model;

import lombok.*;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movie")
@EntityListeners(AuditingEntityListener.class)
public class Movie {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private Long tmdbId;

    @NotNull
    @NotBlank
    @Column(unique = true)
    @Pattern(regexp = "(tt\\d+)")
    private String imdbId;

    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @NotBlank
    @ToString.Exclude
    @Column(length = 2055)
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
    @Column(nullable = false)
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

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Genre.class)
    private List<Genre> genres;

    @Transient
    private Metadata metadata;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;
}
package dev.aminnorouzi.downloadservice.model;

import lombok.*;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "download")
@EntityListeners(AuditingEntityListener.class)
public class Download {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(unique = true)
    private Long movieId;

    @NotNull
    private Long providerId;

    @URL
    @NotNull
    @NotBlank
    private String url;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Type type;

    @NotNull
    @ToString.Exclude
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "downloadId")
    private List<Link> links;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;
}

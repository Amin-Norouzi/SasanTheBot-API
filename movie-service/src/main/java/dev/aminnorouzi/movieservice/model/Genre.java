package dev.aminnorouzi.movieservice.model;

import lombok.*;
import org.hibernate.annotations.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Immutable
@Setter
@Getter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "genre")
public class Genre implements Serializable {

    @Id
    private Integer id;

    private String name;
}

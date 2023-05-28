package dev.aminnorouzi.userservice.model.movie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Genre implements Serializable {

    private Integer id;
    private String name;
}

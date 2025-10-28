package org.example.jpademo.data;

import jakarta.persistence.*;
import lombok.*;

/**
 * An entity/object created to be used within the database.
 * This stores the Pokemon Region itself along with its attributes:
 * id, name, and population.
 **/
@Entity
@Getter @Setter
public class PokemonRegion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private Integer population;
}

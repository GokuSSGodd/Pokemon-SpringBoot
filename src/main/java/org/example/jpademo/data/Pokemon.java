package org.example.jpademo.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * An entity/object created to be used within the database.
 * This stores the Pokemon itself along with its attributes:
 * id, name, level, ability, and region.
 **/
@Entity
@Getter @Setter
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer level;
    private String ability;
    @ManyToOne
    private PokemonRegion region;
}

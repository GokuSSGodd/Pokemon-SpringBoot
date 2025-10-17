package org.example.jpademo.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

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

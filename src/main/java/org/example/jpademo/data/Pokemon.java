package org.example.jpademo.data;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

/**
 * An entity/object created to be used within the database.
 * This stores the Pokemon itself along with its attributes:
 * id, name, level, ability, and region.
 **/
@Entity
@Getter @Setter
@Builder @AllArgsConstructor @NoArgsConstructor
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer level;
    private String ability;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private List<PokemonType> pokemonTypeList;
    @ManyToOne
    private PokemonRegion region;
}

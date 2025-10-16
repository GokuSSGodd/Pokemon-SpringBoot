package org.example.jpademo.data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

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

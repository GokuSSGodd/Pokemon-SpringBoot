package org.example.jpademo.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.jpademo.data.PokemonType;

import java.util.List;

/**
 * Dto is a way to be able to retrieve information from the database in order to update
 * an object within the database as they relate to the Pokemon & its Region.
 * If you wanted to update a Pokemon object by retrieving certain information pertaining
 * to the Pokemon using its region you would do that with this
 **/
@Getter @Setter
public class PokemonDto {
    private Integer id;
    private String name;
    private Integer level;
    private String ability;
    private List<PokemonType> pokemonTypeList;
    private String regionName;
    private Integer regionId;
}

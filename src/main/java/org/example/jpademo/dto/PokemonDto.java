package org.example.jpademo.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import org.example.jpademo.data.PokemonType;
import java.util.List;

/**
 * Dto is a way to be able to retrieve information from the database in order to update
 * an object within the database as they relate to the Pokemon & its Region.
 * If you wanted to update a Pokemon object by retrieving certain information pertaining
 * to the Pokemon using its region you would do that with this
 * Also adds a layer of security to the application
 **/
@Builder
public record PokemonDto (
        @NotBlank
        String name,
        @NotNull @Min(5) @Max(100)
        Integer level,
        @NotBlank
        String ability,
        @NotEmpty
        List<PokemonType> pokemonTypeList,
        @NotBlank
        String regionName
) {}

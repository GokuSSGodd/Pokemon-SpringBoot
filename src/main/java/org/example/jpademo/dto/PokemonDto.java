package org.example.jpademo.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.jpademo.data.PokemonType;
import org.example.jpademo.data.PokemonWeakness;

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
        @NotNull @NotBlank
        String name,
        @NotNull @NotBlank @Min(5) @Max(100)
        Integer level,
        @NotNull @NotBlank
        String ability,
        @NotNull.List({}) @NotBlank.List({})
        List<PokemonType> pokemonTypeList,
        @NotNull.List({}) @NotBlank.List({})
        List<PokemonWeakness> pokemonWeaknessList,
        @NotNull @NotBlank
        String regionName
) {}

package org.example.jpademo.dto;

/**
 * Dto is a way to be able to retrieve information from the database in order to update
 * an object within the database as they relate to the Pokemon & its Region.
 * If you wanted to update a PokemonRegion object by retrieving certain information pertaining
 * to the PokemonRegion using its region you would do that with this
 * Also adds a layer of security to the application
 **/
public record PokemonRegionDto(
        String regionName,
        Integer population
) {}

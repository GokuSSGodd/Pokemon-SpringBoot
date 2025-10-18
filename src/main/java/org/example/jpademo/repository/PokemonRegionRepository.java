package org.example.jpademo.repository;

import org.example.jpademo.data.PokemonRegion;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

/**
 * This is the Crud(Create, Read, Update, Delete) Repository for the Pokemon Region.
 * It has built-in functions that allow you to easily manipulate the database.
 * You can also create your own functions to mainuplate the database.
 * SpringBoot also creates a bean for this repository allowing you to pass the object repository
 * as a reference anywhere, also called dependency injection. A singleton at the moment.
 **/
public interface PokemonRegionRepository extends CrudRepository<PokemonRegion,Integer> {
    Optional<PokemonRegion> findPokemonRegionById(Integer id);
    Optional<PokemonRegion> findPokemonRegionByName(String name);
}

package org.example.jpademo.repository;

import org.example.jpademo.data.Pokemon;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
/**
 * This is the Crud(Create, Read, Update, Delete) Repository for the Pokemon themselves.
 * It has built-in functions that allow you to easily manipulate the database.
 * You can also create your own functions to mainuplate the database.
 * SpringBoot also creates a bean for this repository allowing you to pass the object repository
 * as a reference anywhere, also called dependency injection. A singleton at the moment.
 **/
public interface PokemonRepository extends CrudRepository<Pokemon, Integer> {
    List<Pokemon> findPokemonByAbilityEqualsIgnoreCase(String ability);
    Pokemon findPokemonByName(String name);
}

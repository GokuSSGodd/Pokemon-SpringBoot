package org.example.jpademo.repository;

import org.example.jpademo.data.Pokemon;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PokemonRepository extends CrudRepository<Pokemon, Integer> {
    List<Pokemon> findPokemonByAbilityEqualsIgnoreCase(String ability);
    Pokemon findPokemonByName(String name);
}

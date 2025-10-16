package org.example.jpademo.repository;

import org.example.jpademo.data.PokemonRegion;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PokemonRegionRepository extends CrudRepository<PokemonRegion,Integer> {
    PokemonRegion findPokemonRegionById(Integer id);
    PokemonRegion findPokemonRegionByName(String name);
}

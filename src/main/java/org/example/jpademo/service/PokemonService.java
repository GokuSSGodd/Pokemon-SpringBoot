package org.example.jpademo.service;

import org.example.jpademo.dto.PokemonDto;
import org.example.jpademo.data.Pokemon;
import org.example.jpademo.data.PokemonRegion;
import org.example.jpademo.exception.PokemonException;
import org.example.jpademo.exception.PokemonRegionException;
import org.example.jpademo.repository.PokemonRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * The Service is just another layer that goes between the repository & the controller
 * It allows you to manipulate the database without directly using the controller itself
 * Which adds another layer of security to the application
 * This PokemonService helps the PokemonController manipulate the PokemonRepository
 * through the service itself, it can also be used with other controllers
 **/
@Service
public class PokemonService {
    private final PokemonRepository pokemonRepository;

    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public Pokemon createPokemon(PokemonDto pokemonDto, Optional<PokemonRegion> pokemonRegion) {
        var pokeRegion = pokemonRegion.orElseThrow(()->
                new PokemonRegionException(pokemonDto.regionName())
        );
        var pokemon = new Pokemon();
        pokemon.setName(pokemonDto.name());
        pokemon.setAbility(pokemonDto.ability());
        pokemon.setLevel(pokemonDto.level());
        pokemon.setRegion(pokeRegion);
        return pokemon;
    }

    public void savePokemon(Pokemon pokemon){
        pokemonRepository.save(pokemon);
    }

    public void updatePokemon(PokemonDto pokemonDto, Pokemon pokemon, Optional<PokemonRegion> pokemonRegionOptional) {
        if (pokemonDto.ability() != null && !pokemonDto.ability().isEmpty()) {
            pokemon.setAbility(pokemonDto.ability());
        }
        if (pokemonDto.level() != null && pokemonDto.level() > 0) {
            pokemon.setLevel(pokemonDto.level());
        }
        if (pokemonDto.pokemonTypeList() != null && !pokemonDto.pokemonTypeList().isEmpty()) {
            pokemon.setPokemonTypeList(pokemonDto.pokemonTypeList());
        }
        if (pokemonDto.pokemonWeaknessList() != null && !pokemonDto.pokemonWeaknessList().isEmpty()) {
            pokemon.setPokemonWeaknessList(pokemonDto.pokemonWeaknessList());
        }
        pokemonRegionOptional.ifPresent(pokemon::setRegion);
    }

    public Optional<Pokemon> findPokemonByName(String name){
       return pokemonRepository.findPokemonByName(name);
    }

    public void deletePokemonByName(Pokemon pokemon){
        pokemonRepository.delete(pokemon);
    }

}

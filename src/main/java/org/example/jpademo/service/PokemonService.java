package org.example.jpademo.service;

import org.example.jpademo.Dto.PokemonDto;
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
                new PokemonRegionException(pokemonDto.getRegionName())
        );
        var pokemon = new Pokemon();
        pokemon.setName(pokemonDto.getName());
        pokemon.setAbility(pokemonDto.getAbility());
        pokemon.setLevel(pokemonDto.getLevel());
        pokemon.setRegion(pokeRegion);
        return pokemon;
    }

    public void savePokemon(Pokemon pokemon){
        pokemonRepository.save(pokemon);
    }

    public Pokemon findPokemonByNameFromDto(PokemonDto pokemonDto){
       var pokemonName = pokemonDto.getName();
       var pokemon = pokemonRepository.findPokemonByName(pokemonName);
       return pokemon.orElseThrow(()-> new PokemonException(pokemonName));
    }

    public void updatePokemon(PokemonDto pokemonDto, Pokemon pokemon, Optional<PokemonRegion> pokeRegion) {
        if (pokemonDto.getAbility() != null && !pokemonDto.getAbility().isEmpty()) {
            pokemon.setAbility(pokemonDto.getAbility());
        }
        if (pokemonDto.getLevel() != null && pokemonDto.getLevel() > 0) {
            pokemon.setLevel(pokemonDto.getLevel());
        }
        pokeRegion.ifPresent(pokemon::setRegion);
    }

    public Optional<Pokemon> findPokemonByName(String name){
       return pokemonRepository.findPokemonByName(name);
    }

}

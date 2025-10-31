package org.example.jpademo.service;

import lombok.RequiredArgsConstructor;
import org.example.jpademo.data.PokemonType;
import org.example.jpademo.dto.PokemonDto;
import org.example.jpademo.data.Pokemon;
import org.example.jpademo.data.PokemonRegion;
import org.example.jpademo.repository.PokemonRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * The Service is just another layer that goes between the repository & the controller
 * It allows you to manipulate the database without directly using the controller itself
 * Which adds another layer of security to the application
 * This PokemonService helps the PokemonController manipulate the PokemonRepository
 * through the service itself, it can also be used with other controllers
 **/
@Service
@RequiredArgsConstructor
public class PokemonService {
    private final PokemonRepository pokemonRepository;

    public Pokemon createPokemon(PokemonDto pokemonDto, PokemonRegion pokemonRegion) {
        return Pokemon.builder()
                .name(pokemonDto.name())
                .ability(pokemonDto.ability())
                .level(pokemonDto.level())
                .region(pokemonRegion)
                .pokemonTypeList(pokemonDto.pokemonTypeList())
                .build();
    }

    @Transactional
    public void savePokemon(Pokemon pokemon){
        pokemonRepository.save(pokemon);
    }

    @Transactional
    public void deletePokemon(Pokemon pokemon){ pokemonRepository.delete(pokemon); }

    @Transactional(readOnly = true)
    public Optional<Pokemon> findPokemonByName(String name){
        return pokemonRepository.findPokemonByName(name);
    }

    public void updatePokemon(PokemonDto pokemonDto, Pokemon pokemon, Optional<PokemonRegion> pokemonRegionOptional) {
        if (!pokemon.getAbility().equals(pokemonDto.ability())) {
            pokemon.setAbility(pokemonDto.ability());
        }
        if (pokemon.getLevel() != pokemonDto.level()) {
            pokemon.setLevel(pokemonDto.level());
        }
        if (!pokemon.getPokemonTypeList().equals(pokemonDto.pokemonTypeList())) {
            pokemon.setPokemonTypeList(pokemonDto.pokemonTypeList());
        }
        pokemonRegionOptional.ifPresent(pokemon::setRegion);
    }

    public PokemonDto mapPokemonToDto(Pokemon pokemon) {
        List<PokemonType> copy = new ArrayList<>(pokemon.getPokemonTypeList());
        return PokemonDto.builder()
                .name(pokemon.getName())
                .ability(pokemon.getAbility())
                .level(pokemon.getLevel())
                .pokemonTypeList(copy)
                .regionName(pokemon.getRegion().getName())
                .build();
    }
}

package org.example.jpademo.service;

import org.example.jpademo.data.PokemonType;
import org.example.jpademo.data.PokemonWeakness;
import org.example.jpademo.dto.PokemonDto;
import org.example.jpademo.data.Pokemon;
import org.example.jpademo.data.PokemonRegion;
import org.example.jpademo.exception.PokemonRegionException;
import org.example.jpademo.repository.PokemonRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
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
        pokemon.setPokemonTypeList(pokemonDto.pokemonTypeList());
        setPokemonWeakness(pokemonDto, pokemon);
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
        if (!pokemonDto.pokemonWeaknessList().isEmpty()) {
            setPokemonWeakness(pokemonDto, pokemon);
        }
        pokemonRegionOptional.ifPresent(pokemon::setRegion);
    }

    public void setPokemonWeakness(PokemonDto pokemonDto, Pokemon pokemon){
        if (pokemonDto.pokemonTypeList().contains(PokemonType.BUG)){
            List<PokemonWeakness> setpokemonWeaknessList = Arrays.asList(PokemonWeakness.FIRE, PokemonWeakness.FLYING, PokemonWeakness.ROCK);
            pokemon.setPokemonWeaknessList(setpokemonWeaknessList);
        }
        else if (pokemonDto.pokemonTypeList().contains(PokemonType.DARK)){
            List<PokemonWeakness> setpokemonWeaknessList = Arrays.asList(PokemonWeakness.FIGHTING, PokemonWeakness.BUG, PokemonWeakness.FAIRY);
            pokemon.setPokemonWeaknessList(setpokemonWeaknessList);
        }
        else if (pokemonDto.pokemonTypeList().contains(PokemonType.DRAGON)){
            List<PokemonWeakness> setpokemonWeaknessList = Arrays.asList(PokemonWeakness.ICE, PokemonWeakness.DRAGON, PokemonWeakness.FAIRY);
            pokemon.setPokemonWeaknessList(setpokemonWeaknessList);
        }
        else if (pokemonDto.pokemonTypeList().contains(PokemonType.ELECTRIC)){
            List<PokemonWeakness> setpokemonWeaknessList = Arrays.asList(PokemonWeakness.GROUND);
            pokemon.setPokemonWeaknessList(setpokemonWeaknessList);
        }
        else if (pokemonDto.pokemonTypeList().contains(PokemonType.FAIRY)){
            List<PokemonWeakness> setpokemonWeaknessList = Arrays.asList(PokemonWeakness.POISON, PokemonWeakness.STEEL);
            pokemon.setPokemonWeaknessList(setpokemonWeaknessList);
        }
        else if (pokemonDto.pokemonTypeList().contains(PokemonType.FIGHTING)){
            List<PokemonWeakness> setpokemonWeaknessList = Arrays.asList(PokemonWeakness.FLYING, PokemonWeakness.PSYCHIC, PokemonWeakness.FAIRY);
            pokemon.setPokemonWeaknessList(setpokemonWeaknessList);
        }
        else if (pokemonDto.pokemonTypeList().contains(PokemonType.GHOST)){
            List<PokemonWeakness> setpokemonWeaknessList = Arrays.asList(PokemonWeakness.GHOST, PokemonWeakness.DARK);
            pokemon.setPokemonWeaknessList(setpokemonWeaknessList);
        }
        else if (pokemonDto.pokemonTypeList().contains(PokemonType.GRASS)){
            List<PokemonWeakness> setpokemonWeaknessList = Arrays.asList(PokemonWeakness.FIRE, PokemonWeakness.ICE, PokemonWeakness.FLYING,
                    PokemonWeakness.POISON, PokemonWeakness.BUG);
            pokemon.setPokemonWeaknessList(setpokemonWeaknessList);
        }
        else if (pokemonDto.pokemonTypeList().contains(PokemonType.GROUND)){
            List<PokemonWeakness> setpokemonWeaknessList = Arrays.asList(PokemonWeakness.WATER, PokemonWeakness.GRASS, PokemonWeakness.ICE);
            pokemon.setPokemonWeaknessList(setpokemonWeaknessList);
        }
        else if (pokemonDto.pokemonTypeList().contains(PokemonType.ICE)){
            List<PokemonWeakness> setpokemonWeaknessList = Arrays.asList(PokemonWeakness.FIRE, PokemonWeakness.FIGHTING, PokemonWeakness.ROCK, PokemonWeakness.STEEL);
            pokemon.setPokemonWeaknessList(setpokemonWeaknessList);
        }
        else if (pokemonDto.pokemonTypeList().contains(PokemonType.NORMAL)){
            List<PokemonWeakness> setpokemonWeaknessList = Arrays.asList(PokemonWeakness.FIGHTING);
            pokemon.setPokemonWeaknessList(setpokemonWeaknessList);
        }
        else if (pokemonDto.pokemonTypeList().contains(PokemonType.POISON)){
            List<PokemonWeakness> setpokemonWeaknessList = Arrays.asList(PokemonWeakness.GROUND,  PokemonWeakness.PSYCHIC);
            pokemon.setPokemonWeaknessList(setpokemonWeaknessList);
        }
        else if (pokemonDto.pokemonTypeList().contains(PokemonType.PSYCHIC)){
            List<PokemonWeakness> setpokemonWeaknessList = Arrays.asList(PokemonWeakness.BUG, PokemonWeakness.GHOST,  PokemonWeakness.DARK);
            pokemon.setPokemonWeaknessList(setpokemonWeaknessList);
        }
        else if (pokemonDto.pokemonTypeList().contains(PokemonType.ROCK)){
            List<PokemonWeakness> setpokemonWeaknessList = Arrays.asList(PokemonWeakness.WATER, PokemonWeakness.GRASS,  PokemonWeakness.FIGHTING, PokemonWeakness.STEEL, PokemonWeakness.GROUND);
            pokemon.setPokemonWeaknessList(setpokemonWeaknessList);
        }
        else if (pokemonDto.pokemonTypeList().contains(PokemonType.STEEL)){
            List<PokemonWeakness> setpokemonWeaknessList = Arrays.asList(PokemonWeakness.GROUND, PokemonWeakness.FIRE,  PokemonWeakness.FIGHTING);
            pokemon.setPokemonWeaknessList(setpokemonWeaknessList);
        }
        else if (pokemonDto.pokemonTypeList().contains(PokemonType.WATER)){
            List<PokemonWeakness> setpokemonWeaknessList = Arrays.asList(PokemonWeakness.ELECTRIC, PokemonWeakness.GRASS);
            pokemon.setPokemonWeaknessList(setpokemonWeaknessList);
        }
        else if (pokemonDto.pokemonTypeList().contains(PokemonType.FIRE)){
            List<PokemonWeakness> setpokemonWeaknessList = Arrays.asList(PokemonWeakness.WATER, PokemonWeakness.GROUND, PokemonWeakness.ROCK);
            pokemon.setPokemonWeaknessList(setpokemonWeaknessList);
        }
        else if (pokemonDto.pokemonTypeList().contains(PokemonType.FLYING)){
            List<PokemonWeakness> setpokemonWeaknessList = Arrays.asList(PokemonWeakness.ELECTRIC, PokemonWeakness.ROCK, PokemonWeakness.ICE);
            pokemon.setPokemonWeaknessList(setpokemonWeaknessList);
        }
        else if (pokemonDto.pokemonTypeList().contains(PokemonType.STELLAR)){
            List<PokemonWeakness> setpokemonWeaknessList = Arrays.asList(PokemonWeakness.STELLAR);
            pokemon.setPokemonWeaknessList(setpokemonWeaknessList);
        }
    }


    public Optional<Pokemon> findPokemonByName(String name){
       return pokemonRepository.findPokemonByName(name);
    }

    public void deletePokemonByName(Pokemon pokemon){
        pokemonRepository.delete(pokemon);
    }

}

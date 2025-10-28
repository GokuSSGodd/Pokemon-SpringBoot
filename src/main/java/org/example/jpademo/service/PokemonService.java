package org.example.jpademo.service;

import org.example.jpademo.data.PokemonType;
import org.example.jpademo.data.PokemonWeakness;
import org.example.jpademo.dto.PokemonDto;
import org.example.jpademo.data.Pokemon;
import org.example.jpademo.data.PokemonRegion;
import org.example.jpademo.exception.PokemonRegionException;
import org.example.jpademo.repository.PokemonRepository;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public Pokemon createPokemon(PokemonDto pokemonDto, PokemonRegion pokemonRegion) {
        return Pokemon.builder()
                .name(pokemonDto.name())
                .ability(pokemonDto.ability())
                .level(pokemonDto.level())
                .region(pokemonRegion)
                .pokemonTypeList(pokemonDto.pokemonTypeList())
                /*.pokemonWeaknessList(pokemonDto.pokemonWeaknessList())*/
                .build();
    }

    public void savePokemon(Pokemon pokemon){
        pokemonRepository.save(pokemon);
    }

    public void deletePokemon(Pokemon pokemon){
        pokemonRepository.delete(pokemon);
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
            /*setPokemonWeakness(pokemonDto, pokemon);*/
            pokemon.setPokemonWeaknessList(pokemonDto.pokemonWeaknessList());
        }
        pokemonRegionOptional.ifPresent(pokemon::setRegion);
    }

    public List<PokemonWeakness> setPokemonWeakness(PokemonDto pokemonDto, Pokemon pokemon){
        List<PokemonWeakness> pokemonWeaknessListArray = new ArrayList<>();
        if (pokemonDto.pokemonTypeList().contains(PokemonType.BUG)){
            pokemonWeaknessListArray.add(PokemonWeakness.FIRE);
            pokemonWeaknessListArray.add(PokemonWeakness.FLYING);
            pokemonWeaknessListArray.add(PokemonWeakness.ROCK);
        }
        if (pokemonDto.pokemonTypeList().contains(PokemonType.DARK)){
            pokemonWeaknessListArray.add(PokemonWeakness.FIGHTING);
            pokemonWeaknessListArray.add(PokemonWeakness.BUG);
            pokemonWeaknessListArray.add(PokemonWeakness.FAIRY);
        }
        if (pokemonDto.pokemonTypeList().contains(PokemonType.DRAGON)){
            pokemonWeaknessListArray.add(PokemonWeakness.ICE);
            pokemonWeaknessListArray.add(PokemonWeakness.DRAGON);
            pokemonWeaknessListArray.add(PokemonWeakness.FAIRY);
        }
        if (pokemonDto.pokemonTypeList().contains(PokemonType.ELECTRIC)){
            pokemonWeaknessListArray.add(PokemonWeakness.GROUND);
        }
        if (pokemonDto.pokemonTypeList().contains(PokemonType.FAIRY)){
            pokemonWeaknessListArray.add(PokemonWeakness.POISON);
            pokemonWeaknessListArray.add(PokemonWeakness.STEEL);
        }
        if (pokemonDto.pokemonTypeList().contains(PokemonType.FIGHTING)){
            pokemonWeaknessListArray.add(PokemonWeakness.PSYCHIC);
            pokemonWeaknessListArray.add(PokemonWeakness.FLYING);
            pokemonWeaknessListArray.add(PokemonWeakness.FAIRY);
        }
        if (pokemonDto.pokemonTypeList().contains(PokemonType.GHOST)){
            pokemonWeaknessListArray.add(PokemonWeakness.GHOST);
            pokemonWeaknessListArray.add(PokemonWeakness.DARK);
        }
        if (pokemonDto.pokemonTypeList().contains(PokemonType.GRASS)){
            pokemonWeaknessListArray.add(PokemonWeakness.FIRE);
            pokemonWeaknessListArray.add(PokemonWeakness.FLYING);
            pokemonWeaknessListArray.add(PokemonWeakness.ICE);
            pokemonWeaknessListArray.add(PokemonWeakness.BUG);
            pokemonWeaknessListArray.add(PokemonWeakness.POISON);
        }
        if (pokemonDto.pokemonTypeList().contains(PokemonType.GROUND)){
            pokemonWeaknessListArray.add(PokemonWeakness.WATER);
            pokemonWeaknessListArray.add(PokemonWeakness.GRASS);
            pokemonWeaknessListArray.add(PokemonWeakness.ICE);
        }
        if (pokemonDto.pokemonTypeList().contains(PokemonType.ICE)){
            pokemonWeaknessListArray.add(PokemonWeakness.FIRE);
            pokemonWeaknessListArray.add(PokemonWeakness.FIGHTING);
            pokemonWeaknessListArray.add(PokemonWeakness.ROCK);
            pokemonWeaknessListArray.add(PokemonWeakness.STEEL);
        }
        if (pokemonDto.pokemonTypeList().contains(PokemonType.NORMAL)){
            pokemonWeaknessListArray.add(PokemonWeakness.FIGHTING);
        }
        if (pokemonDto.pokemonTypeList().contains(PokemonType.POISON)){
            pokemonWeaknessListArray.add(PokemonWeakness.GROUND);
            pokemonWeaknessListArray.add(PokemonWeakness.PSYCHIC);
        }
        if (pokemonDto.pokemonTypeList().contains(PokemonType.PSYCHIC)){
            pokemonWeaknessListArray.add(PokemonWeakness.BUG);
            pokemonWeaknessListArray.add(PokemonWeakness.GHOST);
            pokemonWeaknessListArray.add(PokemonWeakness.DARK);
        }
        if (pokemonDto.pokemonTypeList().contains(PokemonType.ROCK)){
            pokemonWeaknessListArray.add(PokemonWeakness.WATER);
            pokemonWeaknessListArray.add(PokemonWeakness.GRASS);
            pokemonWeaknessListArray.add(PokemonWeakness.FIGHTING);
            pokemonWeaknessListArray.add(PokemonWeakness.STEEL);
            pokemonWeaknessListArray.add(PokemonWeakness.GROUND);
        }
        if (pokemonDto.pokemonTypeList().contains(PokemonType.STEEL)){
            pokemonWeaknessListArray.add(PokemonWeakness.GROUND);
            pokemonWeaknessListArray.add(PokemonWeakness.FIRE);
            pokemonWeaknessListArray.add(PokemonWeakness.FIGHTING);
        }
        if (pokemonDto.pokemonTypeList().contains(PokemonType.WATER)){
            pokemonWeaknessListArray.add(PokemonWeakness.ELECTRIC);
            pokemonWeaknessListArray.add(PokemonWeakness.GRASS);
        }
        if (pokemonDto.pokemonTypeList().contains(PokemonType.FIRE)){
            pokemonWeaknessListArray.add(PokemonWeakness.WATER);
            pokemonWeaknessListArray.add(PokemonWeakness.GROUND);
            pokemonWeaknessListArray.add(PokemonWeakness.ROCK);
        }
        if (pokemonDto.pokemonTypeList().contains(PokemonType.FLYING)){
            pokemonWeaknessListArray.add(PokemonWeakness.ELECTRIC);
            pokemonWeaknessListArray.add(PokemonWeakness.ICE);
            pokemonWeaknessListArray.add(PokemonWeakness.ROCK);
        }
        if (pokemonDto.pokemonTypeList().contains(PokemonType.STELLAR)){
            pokemonWeaknessListArray.add(PokemonWeakness.STELLAR);
        }
        removePokemonWeakness(pokemonDto, pokemon, pokemonWeaknessListArray);
        /*pokemon.setPokemonWeaknessList(pokemonWeaknessListArray);*/
        return pokemonWeaknessListArray;
    }

    public void removePokemonWeakness(PokemonDto pokemonDto, Pokemon pokemon, List<PokemonWeakness> pokemonWeaknessListArray){
        if (pokemonDto.pokemonTypeList().contains(PokemonType.GHOST)){
            pokemonWeaknessListArray.remove(PokemonWeakness.NORMAL);
            pokemonWeaknessListArray.remove(PokemonWeakness.FIGHTING);
        }
        if (pokemonDto.pokemonTypeList().contains(PokemonType.NORMAL)){
            pokemonWeaknessListArray.remove(PokemonWeakness.GHOST);
        }
        if (pokemonDto.pokemonTypeList().contains(PokemonType.STEEL)){
            pokemonWeaknessListArray.remove(PokemonWeakness.POISON);
        }
        if (pokemonDto.pokemonTypeList().contains(PokemonType.FLYING)){
            pokemonWeaknessListArray.remove(PokemonWeakness.GROUND);
        }
        if (pokemonDto.pokemonTypeList().contains(PokemonType.DARK)){
            pokemonWeaknessListArray.remove(PokemonWeakness.PSYCHIC);
        }
        if (pokemonDto.pokemonTypeList().contains(PokemonType.FAIRY)){
            pokemonWeaknessListArray.remove(PokemonWeakness.DRAGON);
            pokemonWeaknessListArray.remove(PokemonWeakness.FIGHTING);
        }
        if (pokemonDto.pokemonTypeList().contains(PokemonType.GROUND)){
            pokemonWeaknessListArray.remove(PokemonWeakness.ELECTRIC);
        }
        if (pokemonDto.pokemonTypeList().contains(PokemonType.WATER)){
            pokemonWeaknessListArray.remove(PokemonWeakness.FIRE);
        }
    }

    public Optional<Pokemon> findPokemonByName(String name){
       return pokemonRepository.findPokemonByName(name);
    }

}

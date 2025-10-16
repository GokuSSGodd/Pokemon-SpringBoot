package org.example.jpademo.controller;

import org.example.jpademo.Dto.PokemonDto;
import org.example.jpademo.data.Pokemon;
import org.example.jpademo.data.PokemonRegion;
import org.example.jpademo.repository.PokemonRegionRepository;
import org.example.jpademo.repository.PokemonRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/pokemon")
public class PokemonController{

    private final PokemonRepository pokemonRepository;
    private final PokemonRegionRepository pokemonRegionRepository;

    public PokemonController(PokemonRepository pokemonRepository, PokemonRegionRepository pokemonRegionRepository) {
        this.pokemonRepository = pokemonRepository;
        this.pokemonRegionRepository = pokemonRegionRepository;
    }

    @PostMapping("/add")
    public @ResponseBody String addNewPokemon(@RequestBody PokemonDto pokemonDto){
        PokemonRegion pokeRegion = null;
        if(pokemonDto.getRegionName() != null && !pokemonDto.getRegionName().isEmpty()){
            pokeRegion = pokemonRegionRepository.findPokemonRegionByName((pokemonDto.getRegionName()));
        } else if(pokemonDto.getRegionName() != null){
            pokeRegion = pokemonRegionRepository.findPokemonRegionById(pokemonDto.getRegionId());
        }
        if(pokeRegion == null){
            return "Invalid Region!";
        }
        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonDto.getName());
        pokemon.setAbility(pokemonDto.getAbility());
        pokemon.setLevel(pokemonDto.getLevel());
        pokemon.setRegion(pokeRegion);
        pokemonRepository.save(pokemon);
        return pokemon.getName() + " was added successfully to " +  pokeRegion.getName() + " region!";
    }

    @PutMapping("/update/region")
    public @ResponseBody String updatePokemonRegion(@RequestBody PokemonDto pokemonDto){
        PokemonRegion pokeRegion = null;
        if(pokemonDto.getRegionName() != null && !pokemonDto.getRegionName().isEmpty()){
            pokeRegion = pokemonRegionRepository.findPokemonRegionByName((pokemonDto.getRegionName()));
        } else if(pokemonDto.getRegionName() != null){
            pokeRegion = pokemonRegionRepository.findPokemonRegionById(pokemonDto.getRegionId());
        }
        if(pokeRegion == null){
            return "Invalid Region!";
        }
        var pokemon = pokemonRepository.findPokemonByName(pokemonDto.getName());
        pokemon.setRegion(pokeRegion);
        pokemonRepository.save(pokemon);
        return pokemon.getName() + "'s region was successfully updated to " +  pokeRegion.getName() + " region!";
    }

    @PutMapping("/update")
    public @ResponseBody String updatePokemon(@RequestBody PokemonDto pokemonDto) {
        try {
            PokemonRegion pokeRegion = null;
            var pokemon = pokemonRepository.findPokemonByName((pokemonDto.getName()));
            if(pokemon == null){
                return "Invalid Pokemon!";
            }
            if (pokemonDto.getAbility() != null && !pokemonDto.getAbility().isEmpty()) {
                pokemon.setAbility(pokemonDto.getAbility());
            }
            if (pokemonDto.getLevel() != null && pokemonDto.getLevel() > 0) {
                pokemon.setLevel(pokemonDto.getLevel());
            }
            if (pokemonDto.getRegionName() != null && !pokemonDto.getRegionName().isEmpty()) {
                pokeRegion = pokemonRegionRepository.findPokemonRegionByName(pokemonDto.getRegionName());
            } else if (pokemonDto.getRegionId() != null && pokemonDto.getRegionId() > 0) {
                pokeRegion = pokemonRegionRepository.findPokemonRegionById(pokemonDto.getRegionId());
            }
            if(pokeRegion != null){
                pokemon.setRegion(pokeRegion);
            }
            pokemonRepository.save(pokemon);
            return pokemon.getName() + " was successfully updated!";
        } catch (Exception e) {
            return "Invalid Input";
        }
    }

    @DeleteMapping("/delete")
    public @ResponseBody String deletePokemon(@RequestBody Pokemon pokemon){
        pokemonRepository.delete(pokemon);
        return pokemon.getName() + " was deleted successfully";
    }

}

package org.example.jpademo.controller;

import org.example.jpademo.Dto.PokemonDto;
import org.example.jpademo.data.Pokemon;
import org.example.jpademo.service.PokemonRegionService;
import org.example.jpademo.service.PokemonService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * The controller is essentially a way to call your Rest APIs to
 * Add, Update, Read, & Delete objects within the Pokemon database
 **/
@Controller
@RequestMapping("/pokemon")
public class PokemonController{

    private final PokemonRegionService pokemonRegionService;
    private final PokemonService pokemonService;

    public PokemonController(PokemonRegionService pokemonRegionService, PokemonService pokemonService) {
        this.pokemonRegionService = pokemonRegionService;
        this.pokemonService = pokemonService;
    }

    @PostMapping("/add")
    public @ResponseBody String addNewPokemon(@RequestBody PokemonDto pokemonDto){
        var pokeRegion = pokemonRegionService.getPokemonRegionByDto(pokemonDto);
        Pokemon pokemon = pokemonService.createPokemon(pokemonDto, pokeRegion);
        pokemonService.savePokemon(pokemon);
        return pokemon.getName() + " was added successfully to " + pokeRegion.get().getName() + " region!";
    }

    @PutMapping("/update")
    public @ResponseBody String updatePokemon(@RequestBody PokemonDto pokemonDto) {
            var pokemon = pokemonService.findPokemonByNameFromDto(pokemonDto);
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
    }

/*
    @DeleteMapping("/delete")
    public @ResponseBody String deletePokemon(@RequestBody Pokemon pokemon){
        pokemonRepository.delete(pokemon);
        return pokemon.getName() + " was deleted successfully";
    }*/

}

package org.example.jpademo.controller;

import org.example.jpademo.Dto.PokemonDto;
import org.example.jpademo.data.Pokemon;
import org.example.jpademo.exception.PokemonException;
import org.example.jpademo.service.PokemonRegionService;
import org.example.jpademo.service.PokemonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The controller is essentially a way to call your Rest APIs to
 * Add, Update, Read, & Delete objects within the Pokemon database
 **/
@Controller
@RequestMapping("/pokemon")
public class PokemonController{
    Logger log = LoggerFactory.getLogger(PokemonController.class);
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
        log.info("Pokemon Added Successfully");
        return pokemon.getName() + " was added successfully to " + pokeRegion.get().getName() + " region!";
    }

    @PutMapping("/update")
    public @ResponseBody String updatePokemon(@RequestBody PokemonDto pokemonDto) {
            var pokemon = pokemonService.findPokemonByNameFromDto(pokemonDto);
            var pokeRegion = pokemonRegionService.getPokemonRegionByDto(pokemonDto);
            pokemonService.updatePokemon(pokemonDto,pokemon,pokeRegion);
            pokemonService.savePokemon(pokemon);
            return pokemon.getName() + " was successfully updated!";
    }

    @GetMapping("/get")
    public @ResponseBody Pokemon getPokemon(@RequestParam String name){
        var pokemonOptional = pokemonService.findPokemonByName(name);
        var pokemon = pokemonOptional.orElseThrow(() -> new PokemonException("Pokemon Not Found"));
        return pokemon;
    }


}

package org.example.jpademo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.jpademo.dto.PokemonDto;
import org.example.jpademo.data.Pokemon;
import org.example.jpademo.exception.PokemonException;
import org.example.jpademo.exception.PokemonRegionException;
import org.example.jpademo.service.PokemonRegionService;
import org.example.jpademo.service.PokemonService;
import org.springframework.web.bind.annotation.*;

/**
 * The RestController is essentially a way to call your Rest APIs to
 * Add, Update, Read, & Delete objects within the Pokemon database
 * RestController = Controller + ResponseBody
 * It is designed for RESTful web services & API development
 **/
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/pokemon")
public class PokemonController{
    private final PokemonRegionService pokemonRegionService;
    private final PokemonService pokemonService;

    @PostMapping("/add")
    public String addNewPokemon(@RequestBody PokemonDto pokemonDto){
        var pokeRegionOptional = pokemonRegionService.getPokemonRegionByName(pokemonDto.regionName());
        var pokemonRegion = pokeRegionOptional.orElseThrow(() -> new PokemonRegionException("Pokemon Region Not Found"));
        Pokemon pokemon = pokemonService.createPokemon(pokemonDto, pokemonRegion);
        pokemonService.savePokemon(pokemon);
        return pokemon.getName() + " was added successfully to " + pokemonRegion.getName() + " region!";
    }

    @PutMapping("/update")
    public String updatePokemon(@RequestBody PokemonDto pokemonDto) {
        var pokemonOptional = pokemonService.findPokemonByName(pokemonDto.name());
        var pokemon =  pokemonOptional.orElseThrow(() -> new PokemonException("Pokemon Not Found"));
        var pokemonRegionOptional = pokemonRegionService.getPokemonRegionByName(pokemonDto.regionName());
        pokemonService.updatePokemon(pokemonDto,pokemon,pokemonRegionOptional);
        pokemonService.savePokemon(pokemon);
        return pokemon.getName() + " was successfully updated!";
    }

    @GetMapping("/get")
    public Pokemon getPokemon(@RequestParam String name){
        var pokemonOptional = pokemonService.findPokemonByName(name);
        var pokemon = pokemonOptional.orElseThrow(() -> new PokemonException("Pokemon Not Found"));
        log.info("Pokemon received Successfully");
        return pokemon;
    }

    @DeleteMapping("/delete")
    public String deletePokemon(@RequestParam String name){
       var pokemonOptional = pokemonService.findPokemonByName(name);
       var pokemon = pokemonOptional.orElseThrow(() -> new PokemonException("Pokemon Not Found"));
       pokemonService.deletePokemon(pokemon);
       return pokemon.getName() + " was successfully deleted!";
    }



}

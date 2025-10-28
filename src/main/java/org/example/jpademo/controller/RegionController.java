package org.example.jpademo.controller;

import org.example.jpademo.data.PokemonRegion;
import org.example.jpademo.dto.PokemonRegionDto;
import org.example.jpademo.exception.PokemonRegionException;
import org.example.jpademo.repository.PokemonRegionRepository;
import org.example.jpademo.service.PokemonRegionService;
import org.example.jpademo.service.PokemonService;
import org.springframework.web.bind.annotation.*;

/**
 * The RestController is essentially a way to call your Rest APIs to
 * Add, Update, Read, & Delete objects within the Pokemon Region database
 * RestController = Controller + ResponseBody
 * It is designed for RESTful web services & API development
 **/
@RestController
@RequestMapping("/region")
public class RegionController {
    private final PokemonRegionService pokemonRegionService;
    private final PokemonRegionRepository pokemonRegionRepository;
    private final PokemonService pokemonService;

    public RegionController(PokemonRegionService pokemonRegionService, PokemonRegionRepository pokemonRegionRepository, PokemonService pokemonService) {
        this.pokemonRegionService = pokemonRegionService;
        this.pokemonRegionRepository = pokemonRegionRepository;
        this.pokemonService = pokemonService;
    }

    @PostMapping("/add")
    public String addNewRegion(@RequestBody PokemonRegionDto pokemonRegionDto) {
        PokemonRegion pokemonRegion = pokemonRegionService.createNewPokemonRegion(pokemonRegionDto);
        pokemonRegionService.savePokemonRegion(pokemonRegion);
        return pokemonRegion.getName() + " was added successfully";
    }

    @DeleteMapping("/delete")
    public String deletePokemonRegion(@RequestParam String name) {
        var pokemonRegionOptional = pokemonRegionService.getPokemonRegion(name);
        var pokemonRegion = pokemonRegionOptional.orElseThrow(() -> new PokemonRegionException("Pokemon Region Not Found"));
        pokemonRegionService.deletePokemonRegion(pokemonRegion);
        return  pokemonRegion.getName() + " was deleted successfully";
    }



}

package org.example.jpademo.controller;

import org.example.jpademo.data.PokemonRegion;
import org.example.jpademo.repository.PokemonRegionRepository;
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
    private final PokemonRegionRepository pokemonRegionRepository;
    public RegionController(PokemonRegionRepository pokemonRegionRepository) {
        this.pokemonRegionRepository = pokemonRegionRepository;
    }

    @PostMapping("/add")
    public String addNewRegion(@RequestBody PokemonRegion pokemonRegion){
        pokemonRegionRepository.save(pokemonRegion);
        return pokemonRegion.getName() + " was added successfully";
    }
}

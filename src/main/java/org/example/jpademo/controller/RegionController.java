package org.example.jpademo.controller;

import org.example.jpademo.data.PokemonRegion;
import org.example.jpademo.repository.PokemonRegionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/region")
public class RegionController {
    private final PokemonRegionRepository pokemonRegionRepository;
    public RegionController(PokemonRegionRepository pokemonRegionRepository) {
        this.pokemonRegionRepository = pokemonRegionRepository;
    }

    @PostMapping("/add")
    public @ResponseBody String addNewRegion(@RequestBody PokemonRegion pokemonRegion){
        pokemonRegionRepository.save(pokemonRegion);
        return pokemonRegion.getName() + " was added successfully";
    }
}

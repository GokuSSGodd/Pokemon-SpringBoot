package org.example.jpademo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.jpademo.dto.PokemonDto;
import org.example.jpademo.data.Pokemon;
import org.example.jpademo.exception.PokemonNameNotFoundException;
import org.example.jpademo.exception.PokemonRegionNameNotFoundException;
import org.example.jpademo.service.PokemonRegionService;
import org.example.jpademo.service.PokemonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<PokemonDto> addNewPokemon(@Valid @RequestBody PokemonDto pokemonDto){
        var pokeRegionOptional = pokemonRegionService.getPokemonRegionByName(pokemonDto.regionName());
        var pokemonRegion = pokeRegionOptional.orElseThrow(() -> new PokemonRegionNameNotFoundException(pokemonDto.regionName()));
        Pokemon pokemon = pokemonService.createPokemon(pokemonDto, pokemonRegion);
        pokemonService.savePokemon(pokemon);
        return ResponseEntity.status(HttpStatus.CREATED).body(pokemonDto);
    }

    @PutMapping("/update")
    public ResponseEntity<PokemonDto>  updatePokemon(@Valid @RequestBody PokemonDto pokemonDto) {
        var pokemonOptional = pokemonService.findPokemonByName(pokemonDto.name());
        var pokemon =  pokemonOptional.orElseThrow(() -> new PokemonNameNotFoundException(pokemonDto.name()));
        var pokemonRegionOptional = pokemonRegionService.getPokemonRegionByName(pokemonDto.regionName());
        pokemonService.updatePokemon(pokemonDto,pokemon,pokemonRegionOptional);
        pokemonService.savePokemon(pokemon);
        return ResponseEntity.status(HttpStatus.OK).body(pokemonDto);
    }

    @GetMapping("/get")
    public ResponseEntity<PokemonDto> getPokemon(@RequestParam String name) {
        var pokemonOptional = pokemonService.findPokemonByName(name);
        var pokemon = pokemonOptional.orElseThrow(() -> new PokemonNameNotFoundException(name));
        var pokemonDto = pokemonService.mapPokemonToDto(pokemon);
        log.info("Pokemon received Successfully");
        return ResponseEntity.status(HttpStatus.OK).body(pokemonDto);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<PokemonDto> deletePokemon(@RequestParam String name){
       var pokemonOptional = pokemonService.findPokemonByName(name);
       var pokemon = pokemonOptional.orElseThrow(() -> new PokemonNameNotFoundException(name));
       var pokemonDto = pokemonService.mapPokemonToDto(pokemon);
       pokemonService.deletePokemon(pokemon);
       return ResponseEntity.status(HttpStatus.NO_CONTENT).body(pokemonDto);
    }
}

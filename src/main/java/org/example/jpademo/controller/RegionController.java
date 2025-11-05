package org.example.jpademo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.flogger.Flogger;
import lombok.extern.slf4j.Slf4j;
import org.example.jpademo.data.PokemonRegion;
import org.example.jpademo.dto.PokemonRegionDto;
import org.example.jpademo.exception.PokemonRegionNameNotFoundException;
import org.example.jpademo.service.PokemonRegionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The RestController is essentially a way to call your Rest APIs to
 * Add, Update, Read, & Delete objects within the Pokemon Region database
 * RestController = Controller + ResponseBody
 * It is designed for RESTful web services & API development
 **/
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/region")
public class RegionController {
    private final PokemonRegionService pokemonRegionService;

    @PostMapping("/add")
    public ResponseEntity<PokemonRegionDto> addNewRegion(@Valid @RequestBody PokemonRegionDto pokemonRegionDto) {
        PokemonRegion pokemonRegion = pokemonRegionService.createNewPokemonRegion(pokemonRegionDto);
        pokemonRegionService.savePokemonRegion(pokemonRegion);
        return ResponseEntity.status(HttpStatus.CREATED).body(pokemonRegionDto);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deletePokemonRegion(@RequestParam String name) {
        var pokemonRegionOptional = pokemonRegionService.getPokemonRegionByName(name);
        var pokemonRegion = pokemonRegionOptional.orElseThrow(() -> new PokemonRegionNameNotFoundException(name));
        var pokemonRegionDto = pokemonRegionService.mapPokemonRegionToDto(pokemonRegion);
        pokemonRegionService.deletePokemonRegion(pokemonRegion);
        log.info(String.valueOf(pokemonRegionDto));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

package org.example.jpademo.service;

import org.example.jpademo.data.Pokemon;
import org.example.jpademo.dto.PokemonDto;
import org.example.jpademo.data.PokemonRegion;
import org.example.jpademo.dto.PokemonRegionDto;
import org.example.jpademo.repository.PokemonRegionRepository;
import org.example.jpademo.repository.PokemonRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * The Service is just another layer that goes between the repository & the controller
 * It allows you to manipulate the database without directly using the controller itself
 * Which adds another layer of security to the application
 * This PokemonRegionervice helps the PokemonRegionController manipulate the PokemonRegionRepository
 * through the service itself, it can also be used with other controllers
 **/
@Service
public class PokemonRegionService {
    private final PokemonRegionRepository pokemonRegionRepository;
    private final PokemonRepository pokemonRepository;

    public PokemonRegionService(PokemonRegionRepository pokemonRegionRepository, PokemonRepository pokemonRepository) {
        this.pokemonRegionRepository = pokemonRegionRepository;
        this.pokemonRepository = pokemonRepository;
    }

    public PokemonRegion createNewPokemonRegion(PokemonRegionDto pokemonRegionDto){
        var pokemonRegion = new PokemonRegion();
        pokemonRegion.setName(pokemonRegionDto.regionName());
        pokemonRegion.setPopulation(pokemonRegionDto.population());
        return pokemonRegion;
    }

    public void savePokemonRegion(PokemonRegion pokemonRegion){
        pokemonRegionRepository.save(pokemonRegion);
    }

    public Optional<PokemonRegion> getPokemonRegionByDto(PokemonDto pokemonDto){
        if(pokemonDto.regionName() != null && !pokemonDto.regionName().isEmpty()){
           return pokemonRegionRepository.findPokemonRegionByName(pokemonDto.regionName());
        } else if(pokemonDto.regionId() != null) {
           return pokemonRegionRepository.findPokemonRegionById(pokemonDto.regionId());
        }
         return Optional.empty();
    }

    public Optional<PokemonRegion> getPokemonRegion(String name){
        return pokemonRegionRepository.findPokemonRegionByName(name);
    }

    public void deletePokemonRegion(PokemonRegion pokemonRegion){
        pokemonRegionRepository.delete(pokemonRegion);
    }


}

package org.example.jpademo.service;

import org.example.jpademo.dto.PokemonDto;
import org.example.jpademo.data.PokemonRegion;
import org.example.jpademo.exception.PokemonRegionException;
import org.example.jpademo.repository.PokemonRegionRepository;
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
    public PokemonRegionService(PokemonRegionRepository pokemonRegionRepository) {
        this.pokemonRegionRepository = pokemonRegionRepository;
    }

    public Optional<PokemonRegion> getPokemonRegionByDto(PokemonDto pokemonDto){
        if(pokemonDto.getRegionName() != null && !pokemonDto.getRegionName().isEmpty()){
           return pokemonRegionRepository.findPokemonRegionByName(pokemonDto.getRegionName());
        } else if(pokemonDto.getRegionName() != null) {
           return pokemonRegionRepository.findPokemonRegionById(pokemonDto.getRegionId());
        }
         return Optional.empty();
    }
}

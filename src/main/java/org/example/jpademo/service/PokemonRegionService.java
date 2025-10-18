package org.example.jpademo.service;

import org.example.jpademo.Dto.PokemonDto;
import org.example.jpademo.data.PokemonRegion;
import org.example.jpademo.exception.PokemonRegionException;
import org.example.jpademo.repository.PokemonRegionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
         throw new PokemonRegionException(null);
    }

}

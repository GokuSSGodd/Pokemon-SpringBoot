package org.example.jpademo.service;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.jpademo.data.PokemonRegion;
import org.example.jpademo.dto.PokemonRegionDto;
import org.example.jpademo.repository.PokemonRegionRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * The Service is just another layer that goes between the repository & the controller
 * It allows you to manipulate the database without directly using the controller itself
 * Which adds another layer of security to the application
 * This PokemonRegionService helps the PokemonRegionController manipulate the PokemonRegionRepository
 * through the service itself, it can also be used with other controllers
 **/
@Service
@RequiredArgsConstructor
public class PokemonRegionService {
    private final PokemonRegionRepository pokemonRegionRepository;

    public PokemonRegion createNewPokemonRegion(PokemonRegionDto pokemonRegionDto) {
        return PokemonRegion.builder()
                .name(pokemonRegionDto.regionName())
                .population(pokemonRegionDto.population())
                .build();
    }

    @Transactional
    public void savePokemonRegion(PokemonRegion pokemonRegion){
        pokemonRegionRepository.save(pokemonRegion);
    }

    @Transactional
    public void deletePokemonRegion(PokemonRegion pokemonRegion){ pokemonRegionRepository.delete(pokemonRegion); }

    @Transactional(readOnly = true)
    public Optional<PokemonRegion> getPokemonRegionByName(String name){
        return pokemonRegionRepository.findPokemonRegionByName(name);
    }

    public PokemonRegionDto mapPokemonRegionToDto(PokemonRegion pokemonRegion){
        return PokemonRegionDto.builder()
                .regionName(pokemonRegion.getName())
                .population(pokemonRegion.getPopulation())
                .build();
    }

}

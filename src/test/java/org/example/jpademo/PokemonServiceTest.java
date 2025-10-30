package org.example.jpademo;

import org.example.jpademo.data.Pokemon;
import org.example.jpademo.repository.PokemonRepository;
import org.example.jpademo.service.PokemonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PokemonServiceTest {

    @Mock
    private PokemonRepository pokemonRepository;

    @InjectMocks
    private PokemonService pokemonService;

    public PokemonServiceTest(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindPokemonByName(){
        var pokemon = Pokemon.builder()
                .id(8)
                .name("Charmander")
                .level(5)
                .ability("blaze")
                .build();

        Mockito.when(pokemonService.findPokemonByName(pokemon.getName()))
                .thenReturn(Optional.of(pokemon));

        var pokemonOptional = pokemonService.findPokemonByName(pokemon.getName());
        Assertions.assertTrue(pokemonOptional.isPresent());
        Assertions.assertEquals(pokemon.getName(), pokemonOptional.get().getName());
        Assertions.assertEquals(pokemon.getLevel(), pokemonOptional.get().getLevel());
        Assertions.assertEquals(pokemon.getAbility(), pokemonOptional.get().getAbility());
        Assertions.assertEquals(pokemon.getId(), pokemonOptional.get().getId());
    }


}

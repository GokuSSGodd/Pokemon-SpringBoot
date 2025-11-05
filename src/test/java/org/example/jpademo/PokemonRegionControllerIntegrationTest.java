package org.example.jpademo;

import org.example.jpademo.data.Pokemon;
import org.example.jpademo.dto.PokemonRegionDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PokemonRegionControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate testRestTemplate;

    public String baseUrl(){
        return "http://localhost:" + port + "/region";
    }

    @BeforeEach
    public void setUp(){
        var pokemonRegionDto = PokemonRegionDto.builder()
                .regionName("Kanto")
                .population(151)
                .build();
        testRestTemplate.postForEntity(baseUrl() + "/add", pokemonRegionDto, PokemonRegionDto.class);
    }

    @Test
    public void testAddPokemonRegion() {
        var pokemonRegionDto = PokemonRegionDto.builder()
                .regionName("Johto")
                .population(151)
                .build();
        Assertions.assertNotNull(pokemonRegionDto);

        var pokemonRegionDtoResponseEntity = testRestTemplate.postForEntity(baseUrl() + "/add", pokemonRegionDto, PokemonRegionDto.class);
        Assertions.assertEquals(HttpStatus.CREATED, pokemonRegionDtoResponseEntity.getStatusCode());

        var pokemonRegionDtoResponseEntityBodyUnwrapped = pokemonRegionDtoResponseEntity.getBody();
        Assertions.assertNotNull(pokemonRegionDto);
        Assertions.assertEquals(pokemonRegionDto.regionName(), pokemonRegionDtoResponseEntityBodyUnwrapped.regionName());
        Assertions.assertEquals(pokemonRegionDto.population(), pokemonRegionDtoResponseEntityBodyUnwrapped.population());
    }

    @Test
    public void testGetPokemonRegionByPopulationFail(){
        var pokemonRegionDtoResponseEntity = testRestTemplate.getForEntity(baseUrl() + "/search?population=100", PokemonRegionDto.class);
        Assertions.assertNotEquals(HttpStatus.OK, pokemonRegionDtoResponseEntity.getStatusCode());
    }

    @Test
    public void testDeletePokemonByNamePass(){
       var pokemonRegionDtoResponseEntity =  testRestTemplate.exchange(baseUrl() + "/delete?name=Kanto", HttpMethod.DELETE, null, PokemonRegionDto.class);
       Assertions.assertEquals(HttpStatus.NO_CONTENT, pokemonRegionDtoResponseEntity.getStatusCode());
    }

    @Test
    public void testGetPokemonRegionByNamePass(){
        var pokemonRegionDtoResponseEntity = testRestTemplate.getForEntity(baseUrl() + "/search?name=Kanto", PokemonRegionDto.class);
        Assertions.assertNotEquals(HttpStatus.OK, pokemonRegionDtoResponseEntity.getStatusCode());

        var pokemonRegionDtoResponseEntityBodyUnwrapped = pokemonRegionDtoResponseEntity.getBody();
        Assertions.assertNotNull(pokemonRegionDtoResponseEntityBodyUnwrapped);
        Assertions.assertEquals("Kanto", pokemonRegionDtoResponseEntityBodyUnwrapped.regionName());
        Assertions.assertEquals(151, pokemonRegionDtoResponseEntityBodyUnwrapped.population());
    }


}

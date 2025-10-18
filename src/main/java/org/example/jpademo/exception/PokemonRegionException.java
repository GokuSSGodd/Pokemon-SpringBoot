package org.example.jpademo.exception;

public class PokemonRegionException extends RuntimeException {
    public PokemonRegionException(String name) {
        super("Pokemon region not found: " + name);
    }
}

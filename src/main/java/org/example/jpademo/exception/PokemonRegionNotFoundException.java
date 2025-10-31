package org.example.jpademo.exception;

/**
 * An exception class that allows you to call exceptions throughout
 * your application for PokemonRegion
 **/
public class PokemonRegionNotFoundException extends RuntimeException {
    public PokemonRegionNotFoundException(String message) {
        super(message);
    }
}

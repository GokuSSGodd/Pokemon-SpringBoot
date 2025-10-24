package org.example.jpademo.exception;

/**
 * An exception class that allows you to call exceptions throughout
 * your application for PokemonRegion
 **/
public class PokemonRegionException extends RuntimeException {
    public PokemonRegionException(String message) {
        super(message);
    }
}

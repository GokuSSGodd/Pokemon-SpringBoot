package org.example.jpademo.exception;

/**
 * An exception class that allows you to call exceptions throughout
 * your application for Pokemon when it is not found
 **/
public class PokemonNotFoundException extends RuntimeException {
    public PokemonNotFoundException(String message) {
        super(message);
    }
}

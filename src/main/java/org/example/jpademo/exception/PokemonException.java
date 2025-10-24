package org.example.jpademo.exception;

/**
 * An exception class that allows you to call exceptions throughout
 * your application for Pokemon
 **/
public class PokemonException extends RuntimeException {
    public PokemonException(String message) {
        super(message);
    }
}

package org.example.jpademo.exception;

/**
 * A class that inherits from the PokemonNotFoundException
 * and prints out a message when the Pokemon is not found by its Name
 **/
public class PokemonNameNotFoundException extends PokemonNotFoundException {
    public PokemonNameNotFoundException(String name) {
        super("Pokemon with name " + name + " not found");
    }
}

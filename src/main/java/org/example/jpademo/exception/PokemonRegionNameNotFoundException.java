package org.example.jpademo.exception;

/**
 * A class that inherits from the PokemonRegionNotFoundException
 * and prints out a message when the Region is not found by its Name
 **/
public class PokemonRegionNameNotFoundException extends PokemonRegionNotFoundException {
    public PokemonRegionNameNotFoundException(String name) {
        super("Pokemon Region with name " + name + " not found");
    }
}

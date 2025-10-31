package org.example.jpademo.exception;

public class PokemonNameNotFoundException extends PokemonNotFoundException {
    public PokemonNameNotFoundException(String name) {
        super("Pokemon with name " + name + " not found");
    }
}

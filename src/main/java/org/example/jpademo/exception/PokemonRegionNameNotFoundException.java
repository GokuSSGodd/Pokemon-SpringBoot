package org.example.jpademo.exception;

public class PokemonRegionNameNotFoundException extends PokemonRegionException {
    public PokemonRegionNameNotFoundException(String name) {
        super("Pokemon Region with name " + name + " not found");
    }
}

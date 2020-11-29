package com.example.pokedex.outputter;

import com.example.pokedex.entity.Pokemon;

public class ConsolePokemonOutputter implements IPokemonOutputter {
    @Override
    public void output(Pokemon pokemon) {
        if (pokemon == null) {
            System.out.println("Failed to find the pokemon :( ");
        } else {
            System.out.println("Pokemon # " + pokemon.getId());
            System.out.println("Name : " + pokemon.getName());
            System.out.println("Weight : " + pokemon.getWeight());
            System.out.println("Height : " + pokemon.getHeight());
            if(!pokemon.getDescription().isEmpty() && pokemon.getDescription() != null) {
                System.out.println("Description : " + pokemon.getDescription());
            }
        }
    }
}
 
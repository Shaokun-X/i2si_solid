package com.example.pokedex.adpator;

import com.example.pokedex.entity.Pokemon;


/**
 * 
 * PokemonAdaptor is reponsible for retriving pokemon entities from other sources:
 * database, web API, file......
 * 
 */
public interface IPokemonAdaptor {
    public Pokemon getPokemon(int id);
}

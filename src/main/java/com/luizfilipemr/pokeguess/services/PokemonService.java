package com.luizfilipemr.pokeguess.services;

import com.luizfilipemr.pokeguess.entities.Pokemon;

import java.util.ArrayList;

public interface PokemonService {

    ArrayList<Pokemon> generateRandomPokemonList(int min, int max);

}

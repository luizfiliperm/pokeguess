package com.luizfilipemr.pokeguess.services;

import com.luizfilipemr.pokeguess.entities.Pokemon;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class PokemonServiceTest {

    @Autowired
    PokemonService pokemonService;

    @Test
    public void testGenerateRandomPokemonListBetween1and151() {
        ArrayList<Pokemon> pokemon = pokemonService.generateRandomPokemonList(1, 151);

        assert pokemon.size() == 25;

        for (Pokemon p : pokemon) {
            assert p.getNumber() >= 1 && p.getNumber() <= 151;
            assert p.getName() != null;
            assert p.getSprite() != null;
            assert p.getNumber() != pokemon.stream().filter(pokemon1 -> pokemon1.getNumber().equals(p.getNumber())).count();
        }

        for (Pokemon p : pokemon) {
            System.out.println(p.getNumber() + " " + p.getName() + " " + p.getSprite());
        }
    }

    @Test
    public void testGenerateRandomPokemonListBetween1and700() {
        ArrayList<Pokemon> pokemon = pokemonService.generateRandomPokemonList(1, 700);

        assert pokemon.size() == 25;

        for (Pokemon p : pokemon) {
            assert p.getNumber() >= 1 && p.getNumber() <= 700;
            assert p.getName() != null;
            assert p.getSprite() != null;
            assert p.getNumber() != pokemon.stream().filter(pokemon1 -> pokemon1.getNumber().equals(p.getNumber())).count();
        }

        // print pokemon list
        for (Pokemon p : pokemon) {
            System.out.println(p.getNumber() + " " + p.getName() + " " + p.getSprite());
        }
    }
}

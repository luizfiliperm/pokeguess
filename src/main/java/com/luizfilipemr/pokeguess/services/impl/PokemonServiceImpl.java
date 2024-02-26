package com.luizfilipemr.pokeguess.services.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luizfilipemr.pokeguess.entities.Pokemon;
import com.luizfilipemr.pokeguess.services.PokemonService;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

@Service
public class PokemonServiceImpl implements PokemonService {

    private static final String POKEAPI_BASE_URL = "https://pokeapi.co/api/v2/pokemon/";

    @Override
    public ArrayList<Pokemon> generateRandomPokemonList(int min, int max) {

        ArrayList<Pokemon> pokemonList = new ArrayList<>();

        for(int i = 0; i < 25; i++){
            int pokemonId = generateUniqueRandomNumber(pokemonList, min, max);
            Pokemon pokemon = fetchPokemon(pokemonId);
            if(pokemon != null){
                pokemonList.add(pokemon);
            }
        }
        return pokemonList;
    }

    private boolean isDuplicate(ArrayList<Pokemon> pokemonList, int pokemonId){
        return pokemonList.stream()
                .anyMatch(pokemon -> pokemon.getNumber() == pokemonId);
    }

    private int generateUniqueRandomNumber(ArrayList<Pokemon> pokemonList, int min, int max){
        int pokemonId = (int) (Math.random() * (max - min + 1) + min);
        if(isDuplicate(pokemonList, pokemonId)){
            return generateUniqueRandomNumber(pokemonList,min, max);
        }
        return pokemonId;
    }

    private Pokemon fetchPokemon(int pokemonId) {
        try {
            String url = POKEAPI_BASE_URL + pokemonId;

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode json = objectMapper.readTree(response.body());

            String name = json.get("name").asText();
            String spriteUrl = json.get("sprites").get("front_default").asText();

            return new Pokemon(pokemonId, name, spriteUrl);

        }catch (Exception e){
            e.printStackTrace();

            return null;
        }
    }
}

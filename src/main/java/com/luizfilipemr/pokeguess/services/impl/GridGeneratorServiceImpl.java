package com.luizfilipemr.pokeguess.services.impl;

import com.luizfilipemr.pokeguess.entities.Pokemon;
import com.luizfilipemr.pokeguess.services.GridGeneratorService;
import com.luizfilipemr.pokeguess.services.PokemonService;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class GridGeneratorServiceImpl implements GridGeneratorService {

    private static final String REPORT_PATH = "src/main/resources/templates/Grid.jrxml";

    @Autowired
    PokemonService pokemonService;

    @Override
    public byte[] generatePokemonGrid(int min, int max) {
        try{
            JasperReport report = JasperCompileManager.compileReport(REPORT_PATH);

            JasperPrint print = JasperFillManager.fillReport(report, getParameters(getPokemonList(min, max)), new JREmptyDataSource());

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            JasperExportManager.exportReportToPdfStream(print, out);

            return out.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    private ArrayList<Pokemon> getPokemonList(int min, int max){
        return pokemonService.generateRandomPokemonList(min, max);
    }

    private Map<String, Object> getParameters(ArrayList<Pokemon> pokemon){
        Map<String, Object> parameters = new HashMap<>();
        for (Pokemon p : pokemon){
            String pokemonName = p.getName().substring(0, 1).toUpperCase() + p.getName().substring(1);
            parameters.put(("pokemon_name_" + p.getPosition()), pokemonName);
            parameters.put(("pokemon_sprite_" + p.getPosition()), p.getSprite());
        }
        return parameters;
    }
}

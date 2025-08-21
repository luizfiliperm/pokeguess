package com.luizfilipemr.pokeguess.controllers;

import com.luizfilipemr.pokeguess.services.GridGeneratorService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/pokemon")
public class PokemonGridController {
    @Autowired
    GridGeneratorService gridGeneratorService;

    @GetMapping("/grid")
    public ResponseEntity<InputStreamResource> generatePokemonGrid(@RequestParam(value = "min") int min, @RequestParam(value = "max") int max) {
        try {
            byte[] pdf = gridGeneratorService.generatePokemonGrid(min, max);
            if(pdf != null){
                HttpHeaders headers = new HttpHeaders();

                headers.add("Content-Disposition", "inline; filename=pokemon_grid_5x5.pdf");
                return ResponseEntity
                        .ok()
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_PDF)
                        .body(new InputStreamResource(new ByteArrayInputStream(pdf)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }
}

package com.luizfilipemr.pokeguess.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pokemon {

    private Integer position;

    private Integer number;

    private String name;

    private String sprite;
}

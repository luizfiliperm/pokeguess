package com.luizfilipemr.pokeguess.entities;

public class Pokemon {

    private Integer position;
    private Integer number;
    private String name;
    private String sprite;

    public Pokemon(Integer position, Integer number, String name, String sprite) {
        this.position = position;
        this.number = number;
        this.name = name;
        this.sprite = sprite;
    }

    public Pokemon() {}

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }
}

package com.example.pokedex.entity;


/**
 * 
 * Pokemon class reprents the static pokemon entity.
 * 
 * 
 */
public class Pokemon {
    private int id;
    private String name;
    private int height;
    private int weight;
    private String description;

    public Pokemon(int id, String name, int height, int weight, String description) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.description = description;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

}

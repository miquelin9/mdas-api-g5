package com.ccm.pokemon.pokemon.domain.valueObjects;

public class Name {
    public Name(String name) {
        this.name = name;
    }

    private String name;

    public String getName() {
        return this.name;
    }
}

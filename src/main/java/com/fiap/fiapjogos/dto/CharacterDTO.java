package com.fiap.fiapjogos.dto;

import com.fiap.fiapjogos.entity.Character;

public class CharacterDTO {

    private Integer id;
    private String name;

    public CharacterDTO(){}

    public CharacterDTO(Character character) {
        this.id = character.getId();
        this.name = character.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

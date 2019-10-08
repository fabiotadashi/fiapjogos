package com.fiap.fiapjogos.dto;

import com.fiap.fiapjogos.entity.Category;
import com.fiap.fiapjogos.entity.Game;

public class GameDTO {

    private Integer id;
    private String name;
    private Category category;

    public GameDTO(Game game) {
        this.id = game.getId();
        this.name = game.getName();
        this.category = game.getCategory();
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

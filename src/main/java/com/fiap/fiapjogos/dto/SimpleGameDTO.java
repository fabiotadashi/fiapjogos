package com.fiap.fiapjogos.dto;

import com.fiap.fiapjogos.entity.Game;

public class SimpleGameDTO {

    private Integer id;
    private String name;
    private String imageUrl;

    public SimpleGameDTO() {
    }

    public SimpleGameDTO(Game game) {
        this.id = game.getId();
        this.name = game.getName();
        this.imageUrl = game.getImageUrl();
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

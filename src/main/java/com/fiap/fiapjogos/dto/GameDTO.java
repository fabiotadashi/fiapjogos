package com.fiap.fiapjogos.dto;

import com.fiap.fiapjogos.entity.Category;
import com.fiap.fiapjogos.entity.Character;
import com.fiap.fiapjogos.entity.Game;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GameDTO {

    private Integer id;
    private String name;
    private Category category;
    private LocalDate releaseDate;
    private String rating;
    private String imageUrl;
    private List<CharacterDTO> characterList;

    public GameDTO(){}

    public GameDTO(Game game) {
        this.id = game.getId();
        this.name = game.getName();
        this.category = game.getCategory();
        this.releaseDate = game.getReleaseDate();
        this.rating = game.getRating();
        this.imageUrl = game.getImageUrl();
        this.characterList = game.getCharacterList()
                                    .stream()
                                    .map(CharacterDTO::new)
                                    .collect(Collectors.toList());
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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public List<CharacterDTO> getCharacterList() {
        return characterList;
    }

    public void setCharacterList(List<CharacterDTO> characterList) {
        this.characterList = characterList;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}

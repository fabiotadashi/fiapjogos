package com.fiap.fiapjogos.controller;

import com.fiap.fiapjogos.dto.*;
import com.fiap.fiapjogos.service.CharacterService;
import com.fiap.fiapjogos.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("games/{gameId}/characters/")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CharacterDTO insertGame(@PathVariable Integer gameId, @RequestBody CreateCharacterDTO createCharacterDTO) {
        return characterService.insert(gameId, createCharacterDTO);
    }

    @PutMapping("{characterId}")
    public CharacterDTO updateGame(@PathVariable Integer characterId, @RequestBody CreateCharacterDTO createCharacterDTO) {
        return characterService.update(characterId, createCharacterDTO);
    }

    @DeleteMapping("{characterId}")
    public void deleteGame(@PathVariable Integer characterId){
        characterService.delete(characterId);
    }

}

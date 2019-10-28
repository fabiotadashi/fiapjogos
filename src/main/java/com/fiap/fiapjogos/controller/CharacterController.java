package com.fiap.fiapjogos.controller;

import com.fiap.fiapjogos.dto.*;
import com.fiap.fiapjogos.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("games/{gameId}/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CharacterDTO addCharacter(@PathVariable Integer gameId,
                                     @RequestBody @Valid CreateCharacterDTO createCharacterDTO) {
        return characterService.addCharacter(gameId, createCharacterDTO);
    }

    @PutMapping("{characterId}")
    public CharacterDTO updateCharacter(@PathVariable Integer characterId,
                              @RequestBody @Valid CreateCharacterDTO createCharacterDTO){
        return characterService.updateCharacter(characterId, createCharacterDTO);
    }

    @DeleteMapping("{characterId}")
    public void deleteGame(@PathVariable Integer characterId){
        characterService.delete(characterId);
    }

}

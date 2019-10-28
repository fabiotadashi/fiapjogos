package com.fiap.fiapjogos.controller;

import com.fiap.fiapjogos.dto.CharacterDTO;
import com.fiap.fiapjogos.dto.CreateCharacterDTO;
import com.fiap.fiapjogos.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("games/{gameId}/characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @PostMapping
    public CharacterDTO addCharacter(@PathVariable Integer gameId,
                                     @RequestBody CreateCharacterDTO createCharacterDTO) {
        return characterService.addCharacter(gameId, createCharacterDTO);
    }

    @DeleteMapping("{characterId}")
    public void deleteCharacter(@PathVariable Integer gameId,
                                @PathVariable Integer characterId) {
        characterService.delete(gameId, characterId);
    }

}

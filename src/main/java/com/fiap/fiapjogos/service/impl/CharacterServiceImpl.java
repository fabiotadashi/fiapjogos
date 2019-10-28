package com.fiap.fiapjogos.service.impl;

import com.fiap.fiapjogos.dto.CharacterDTO;
import com.fiap.fiapjogos.dto.CreateCharacterDTO;
import com.fiap.fiapjogos.entity.Character;
import com.fiap.fiapjogos.entity.Game;
import com.fiap.fiapjogos.repository.CharacterRepository;
import com.fiap.fiapjogos.repository.GameRepository;
import com.fiap.fiapjogos.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    @Override
    public CharacterDTO addCharacter(Integer gameId, CreateCharacterDTO createCharacterDTO) {
        Character character = new Character();
        character.setName(createCharacterDTO.getName());
        character.setImageUrl(createCharacterDTO.getImageUrl());

        Game game = new Game();
        game.setId(gameId);
        character.setGame(game);

        Character savedCharacter = characterRepository.save(character);

        return new CharacterDTO(savedCharacter);
    }

    @Override
    public void delete(Integer gameId, Integer characterId) {
        Character character = characterRepository.findById(characterId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if(!character.getGame().getId().equals(gameId)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect game");
        }

        characterRepository.delete(character);
    }

}

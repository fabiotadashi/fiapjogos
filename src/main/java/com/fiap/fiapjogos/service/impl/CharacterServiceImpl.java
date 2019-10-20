package com.fiap.fiapjogos.service.impl;

import com.fiap.fiapjogos.dto.CharacterDTO;
import com.fiap.fiapjogos.dto.CreateCharacterDTO;
import com.fiap.fiapjogos.entity.Character;
import com.fiap.fiapjogos.entity.Game;
import com.fiap.fiapjogos.repository.CharacterRepository;
import com.fiap.fiapjogos.service.CharacterService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CharacterServiceImpl implements CharacterService {

    @Autowired
    private CharacterRepository repository;

    @Override
    public CharacterDTO insert(Integer gameId, CreateCharacterDTO createCharacterDTO) {
        Character character = new Character();
        BeanUtils.copyProperties(createCharacterDTO, character);
        Game game = new Game();
        game.setId(gameId);
        character.setGame(game);
        Character savedCharacter = repository.save(character);
        return new CharacterDTO(savedCharacter);
    }

    @Override
    public CharacterDTO update(Integer characterId, CreateCharacterDTO createCharacterDTO) {
        Character character = repository.findById(characterId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        character.setName(createCharacterDTO.getName());
        Character savedCharacter = repository.save(character);
        return new CharacterDTO(savedCharacter);
    }

    @Override
    public void delete(Integer gameId) {
        repository.deleteById(gameId);
    }
}

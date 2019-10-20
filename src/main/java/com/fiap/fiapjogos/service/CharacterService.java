package com.fiap.fiapjogos.service;

import com.fiap.fiapjogos.dto.CharacterDTO;
import com.fiap.fiapjogos.dto.CreateCharacterDTO;

public interface CharacterService {

    CharacterDTO insert(Integer gameId, CreateCharacterDTO createCharacterDTO);

    CharacterDTO update(Integer characterId, CreateCharacterDTO createCharacterDTO);

    void delete(Integer gameId);

}

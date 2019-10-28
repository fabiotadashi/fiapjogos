package com.fiap.fiapjogos.service;

import com.fiap.fiapjogos.dto.CharacterDTO;
import com.fiap.fiapjogos.dto.CreateCharacterDTO;

public interface CharacterService {

    CharacterDTO addCharacter(Integer gameId, CreateCharacterDTO createCharacterDTO);

    void delete(Integer gameId, Integer characterId);
}

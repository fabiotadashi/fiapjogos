package com.fiap.fiapjogos.service;

import com.fiap.fiapjogos.dto.CharacterDTO;
import com.fiap.fiapjogos.dto.CreateCharacterDTO;

public interface CharacterService {

    CharacterDTO addCharacter(Integer gameId, CreateCharacterDTO createCharacterDTO);

    CharacterDTO updateCharacter(Integer characterId, CreateCharacterDTO createCharacterDTO);

    void delete(Integer characterId);

}

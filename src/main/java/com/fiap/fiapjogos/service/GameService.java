package com.fiap.fiapjogos.service;

import com.fiap.fiapjogos.dto.CreateGameDTO;
import com.fiap.fiapjogos.dto.GameDTO;
import com.fiap.fiapjogos.dto.SimpleGameDTO;

import java.util.List;

public interface GameService {

    List<SimpleGameDTO> getGameList(String name);
    GameDTO getGameById(Integer gameId);
    GameDTO insertGame(CreateGameDTO createGameDTO);
    GameDTO updateGame(Integer gameId, CreateGameDTO createGameDTO);
    void deleteGame(Integer gameId);

}

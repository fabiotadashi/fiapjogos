package com.fiap.fiapjogos.service.impl;

import com.fiap.fiapjogos.GameRepository;
import com.fiap.fiapjogos.dto.CreateGameDTO;
import com.fiap.fiapjogos.dto.GameDTO;
import com.fiap.fiapjogos.dto.SimpleGameDTO;
import com.fiap.fiapjogos.entity.Game;
import com.fiap.fiapjogos.service.GameService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    @Value("${turma.fiap}")
    private String turma;

    @Autowired
    private GameRepository repository;

    @Override
    public List<SimpleGameDTO> getGameList(String name) {
        List<Game> gameList;
        if (name == null) {
            gameList = repository.findAll();
        } else {
            gameList = repository.findAllByNameContaining(name);
        }
        return gameList
                .stream()
                .map(SimpleGameDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public GameDTO getGameById(Integer gameId) {
        Game game = findGameById(gameId);
        game.setName(game.getName() + " - " + turma);
        return new GameDTO(game);
    }

    @Override
    public GameDTO insertGame(CreateGameDTO createGameDTO) {
        Game game = new Game();
        BeanUtils.copyProperties(createGameDTO, game);
        Game savedGame = repository.save(game);
        return new GameDTO(savedGame);
    }

    @Override
    public GameDTO updateGame(Integer gameId, CreateGameDTO createGameDTO) {
        Game game = findGameById(gameId);
        BeanUtils.copyProperties(createGameDTO, game);
        Game savedGame = repository.save(game);
        return new GameDTO(savedGame);
    }

    @Override
    public void deleteGame(Integer gameId) {
        repository.deleteById(gameId);
    }

    private Game findGameById(Integer gameId) {
        return repository.findAllById(Collections.singletonList(gameId))
                .stream()
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}

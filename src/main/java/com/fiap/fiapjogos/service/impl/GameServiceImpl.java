package com.fiap.fiapjogos.service.impl;

import com.fiap.fiapjogos.dto.CreateGameDTO;
import com.fiap.fiapjogos.dto.GameDTO;
import com.fiap.fiapjogos.dto.SimpleGameDTO;
import com.fiap.fiapjogos.entity.Category;
import com.fiap.fiapjogos.entity.Game;
import com.fiap.fiapjogos.repository.GameRepository;
import com.fiap.fiapjogos.service.GameService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
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
        List<Game> gameList = repository.findAll();
        return gameList.stream()
                .filter(game -> name == null || game.getName().startsWith(name))
                .map(SimpleGameDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public GameDTO getGameById(Integer gameId) {
        Game game = findGameById(gameId);
        game.setName(game.getName() + " - " +turma);
        return new GameDTO(game);
    }

    @Override
    public GameDTO insertGame(CreateGameDTO createGameDTO) {
        Game game = new Game();
        game.setName(createGameDTO.getName());
        game.setCategory(createGameDTO.getCategory());
        game.setReleaseDate(createGameDTO.getReleaseDate());
        game.setRating(createGameDTO.getRating());
        Game savedGame = repository.save(game);
        return new GameDTO(savedGame);
    }

    @Override
    public GameDTO updateGame(Integer gameId, CreateGameDTO createGameDTO) {
        Game game = findGameById(gameId);
        BeanUtils.copyProperties(createGameDTO, game);
        Game updatedGame = repository.save(game);
        return new GameDTO(updatedGame);
    }

    @Override
    public void deleteGame(Integer gameId) {
        repository.deleteById(gameId);
    }

    private Game findGameById(Integer gameId) {
        return repository.findById(gameId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}

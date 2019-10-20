package com.fiap.fiapjogos.service.impl;

import com.fiap.fiapjogos.GameRepository;
import com.fiap.fiapjogos.dto.CreateGameDTO;
import com.fiap.fiapjogos.dto.GameDTO;
import com.fiap.fiapjogos.dto.SimpleGameDTO;
import com.fiap.fiapjogos.entity.Category;
import com.fiap.fiapjogos.entity.Game;
import com.fiap.fiapjogos.service.GameService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
        return repository.findAll()
                .stream()
                .filter(game -> name == null || game.getName().startsWith(name))
                .map(SimpleGameDTO::new)
                .collect(Collectors.toList());
//        return gameList.stream()
//                .filter(game -> name == null || game.getName().startsWith(name))
//                .map(SimpleGameDTO::new)
//                .collect(Collectors.toList());
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
        game.setImageUrl(createGameDTO.getImageUrl());
        Game savedGame = repository.save(game);
        return new GameDTO(savedGame);
    }

    @Override
    public GameDTO updateGame(Integer gameId, CreateGameDTO createGameDTO) {
        Game game = findGameById(gameId);
        BeanUtils.copyProperties(createGameDTO, game);
        return new GameDTO(game);
    }

    @Override
    public void deleteGame(Integer gameId) {
        Game game = findGameById(gameId);
        repository.delete(game);
    }

    private Game findGameById(Integer gameId) {
        return repository.findAllById(Collections.singletonList(gameId))
                .stream()
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
//        return gameList.stream()
//                .filter(game -> game.getId().equals(gameId))
//                .findFirst()
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private List<Game> getGamesMock() {
        List<Game> gameList = new ArrayList<>();

        Game game1 = new Game();
        game1.setId(1);
        game1.setName("Lolzin");
        game1.setReleaseDate(LocalDate.now());
        game1.setRating("Livre");
        game1.setCategory(Category.RPG);
        gameList.add(game1);

        Game game2 = new Game();
        game2.setId(2);
        game2.setName("CS:GO");
        game2.setReleaseDate(LocalDate.now());
        game2.setRating("+12");
        game2.setCategory(Category.ACTION);
        gameList.add(game2);

        Game game3 = new Game();
        game3.setId(3);
        game3.setName("Zelda");
        game3.setReleaseDate(LocalDate.now());
        game3.setRating("Livre");
        game3.setCategory(Category.ADVENTURE);
        gameList.add(game3);
        return gameList;
    }

}

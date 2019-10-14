package com.fiap.fiapjogos.controller;

import com.fiap.fiapjogos.dto.CreateGameDTO;
import com.fiap.fiapjogos.dto.GameDTO;
import com.fiap.fiapjogos.dto.SimpleGameDTO;
import com.fiap.fiapjogos.entity.Category;
import com.fiap.fiapjogos.entity.Game;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("games")
public class GameController {

    private List<Game> gameList = getGamesMock();

    @GetMapping
    public List<SimpleGameDTO> getGameList(@RequestParam(required = false) String name) {

        return gameList.stream()
                .filter(game -> name == null || game.getName().startsWith(name))
                .map(SimpleGameDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("{gameId}")
    public GameDTO getGameById(@PathVariable Integer gameId){
        Game game = findGameById(gameId);
        return new GameDTO(game);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GameDTO insertGame(@RequestBody CreateGameDTO createGameDTO) {
        Game game = new Game();
        game.setId(gameList.size() + 1);
        game.setName(createGameDTO.getName());
        game.setCategory(createGameDTO.getCategory());
        game.setReleaseDate(createGameDTO.getReleaseDate());
        game.setRating(createGameDTO.getRating());
        gameList.add(game);
        return new GameDTO(game);
    }

    @PutMapping("{gameId}")
    public GameDTO updateGame(@PathVariable Integer gameId,
                              @RequestBody CreateGameDTO createGameDTO){
        Game game = findGameById(gameId);
        BeanUtils.copyProperties(createGameDTO, game);
        return new GameDTO(game);
    }

    private Game findGameById(Integer gameId) {
        return gameList.stream()
                .filter(game -> game.getId().equals(gameId))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
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

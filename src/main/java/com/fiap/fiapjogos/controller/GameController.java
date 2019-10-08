package com.fiap.fiapjogos.controller;

import com.fiap.fiapjogos.dto.GameDTO;
import com.fiap.fiapjogos.entity.Category;
import com.fiap.fiapjogos.entity.Game;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("games")
public class GameController {

    private List<Game> gameList = getGamesMock();

    @GetMapping
    public List<GameDTO> getGameList(@RequestParam(required = false) String name) {

        return gameList.stream()
                .filter(game -> name == null || game.getName().startsWith(name))
                .map(GameDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public GameDTO insertGame() {
        Game game = new Game();
        game.setId(gameList.size() + 1);
        game.setName("Novo jogo");
        game.setCategory(Category.OTHER);
        game.setReleaseDate(LocalDate.now());
        game.setRating("+18");
        gameList.add(game);
        return new GameDTO(game);
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
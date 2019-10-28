package com.fiap.fiapjogos.controller;

import com.fiap.fiapjogos.dto.CreateGameDTO;
import com.fiap.fiapjogos.dto.GameDTO;
import com.fiap.fiapjogos.dto.SimpleGameDTO;
import com.fiap.fiapjogos.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    public List<SimpleGameDTO> getGameList(@RequestParam(required = false) String name) {
        return gameService.getGameList(name);
    }

    @GetMapping("{gameId}")
    public GameDTO getGameById(@PathVariable Integer gameId) {
        return gameService.getGameById(gameId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GameDTO insertGame(@RequestBody @Valid CreateGameDTO createGameDTO) {
        return gameService.insertGame(createGameDTO);
    }

    @PutMapping("{gameId}")
    public GameDTO updateGame(@PathVariable Integer gameId,
                              @RequestBody CreateGameDTO createGameDTO) {
        return gameService.updateGame(gameId, createGameDTO);
    }

    @DeleteMapping("{gameId}")
    public void deleteGame(@PathVariable Integer gameId) {
        gameService.deleteGame(gameId);
    }

}

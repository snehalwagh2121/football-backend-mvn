package com.bits.to.bytes.players.controller;

import com.bits.to.bytes.players.model.Players;
import com.bits.to.bytes.players.service.PlayersService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/players")
public class PlayersController {
    Logger logger= LoggerFactory.getLogger(PlayersController.class);

    @Autowired
    PlayersService playersService;

    Gson gson= new Gson();

    @GetMapping("/getplayers")
    public ResponseEntity<List<Players>> getPlayersbyTeamnSeason(@RequestParam int teamId, @RequestParam int season){
        logger.info("inside controller of players");
        logger.info("get players of team: "+teamId+" for season: "+season);
        List<Players> players= playersService.getPlayers(teamId, season);
        if(players!=null){
            logger.info("players found: response: "+gson.toJson(players));
            return new ResponseEntity<>(players, HttpStatus.OK);
        }
        logger.info("players not found response: "+gson.toJson(players));
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}

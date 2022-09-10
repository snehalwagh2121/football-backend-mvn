package com.bits.to.bytes.teams.controller;

import com.bits.to.bytes.teams.dbmapper.TeamsDBMapper;
import com.bits.to.bytes.teams.service.TeamsService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:9005", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/teams")
public class TeamsController {
    Logger logger = LoggerFactory.getLogger(TeamsController.class);

    @Autowired
    TeamsService teamsService;

    Gson gson = new Gson();

    @GetMapping("/getteamsbyleague/league/{leagueId}")
    public ResponseEntity<List<TeamsDBMapper>> getTeamsByLeagueId(@PathVariable int leagueId) {
        logger.info("getting teams for league: " + leagueId);
        List<TeamsDBMapper> teamsDBMappers = teamsService.getTeamsList(leagueId);
        if (teamsDBMappers == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        logger.info("response for teams api: " + gson.toJson(teamsDBMappers));
        return new ResponseEntity<>(teamsDBMappers, HttpStatus.OK);
    }

//    @GetMapping("/getseasons/{teamId}")
//    public ResponseEntity<List<Integer>> getTeamSeasons(@PathVariable int teamId) {
//        logger.info("getting seasons for teamId: " + teamId);
//        List<Integer> teamSeasons = teamsService.fetchTeamSeasons(teamId);
//        if (teamSeasons == null)
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//        logger.info("response for team seasons api: " + gson.toJson(teamSeasons));
//        return new ResponseEntity<>(teamSeasons, HttpStatus.OK);
//    }


}

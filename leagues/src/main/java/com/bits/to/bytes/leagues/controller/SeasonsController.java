package com.bits.to.bytes.leagues.controller;

import com.bits.to.bytes.leagues.dbmapper.SeasonsDBMapper;
import com.bits.to.bytes.leagues.service.SeasonService;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/seasons")
public class SeasonsController {
    Logger logger= LoggerFactory.getLogger(SeasonsController.class);

    @Autowired
    SeasonService seasonService;

    Gson gson= new Gson();

    @GetMapping("/getseasonsbyleague/{leagueId}")
    public ResponseEntity<List<SeasonsDBMapper>> getSeasonsForleague(@PathVariable int leagueId) {
        logger.info("getting seasons for leagueId: " + leagueId);
        List<SeasonsDBMapper> seasons = seasonService.fetchSeasons(leagueId);
        if (seasons == null || seasons.size()<1)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        logger.info("response for seasons api: " + gson.toJson(seasons));
        return new ResponseEntity<>(seasons, HttpStatus.OK);
    }
}

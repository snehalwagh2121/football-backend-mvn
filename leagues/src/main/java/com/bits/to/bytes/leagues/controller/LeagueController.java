package com.bits.to.bytes.leagues.controller;

import com.bits.to.bytes.leagues.dbmapper.LeagueDBMapper;
import com.bits.to.bytes.leagues.model.League;
import com.bits.to.bytes.leagues.service.LeagueService;
import com.bits.to.bytes.leagues.util.ExtAPICalls;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/league")
public class LeagueController {
    Logger logger = LoggerFactory.getLogger(LeagueController.class);

    @Autowired
    LeagueService leagueService;

    @GetMapping("/listall")
    public ResponseEntity<List<LeagueDBMapper>> getAllLeagues() {
        logger.info("Scheduler to get leagues started");
        List<LeagueDBMapper> leagues = leagueService.getLeagues();
        if(leagues!=null && leagues.size()>0){
            logger.info("response: "+leagues);
            logger.info("Scheduler to get leagues ended: leagues count: " + leagues.size());
            return new ResponseEntity<>(leagues, HttpStatus.OK);
        }else{
            logger.info("leagues is null ");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/byseason/{year}")
    public ResponseEntity<List<LeagueDBMapper>> getLeaguesBySeason(@PathVariable int year) {
        logger.info("Scheduler to get leagues started");
        List<LeagueDBMapper> leagues = leagueService.getLeaguesBySeason(year);
        if(leagues!=null && leagues.size()>0){
            logger.info("response: "+leagues);
            logger.info("Scheduler to get leagues ended: leagues count: " + leagues.size());
            return new ResponseEntity<>(leagues, HttpStatus.OK);
        }else{
            logger.info("response: "+leagues);
            logger.info("Scheduler to get leagues ended: leagues count: " + leagues.size());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}

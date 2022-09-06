package com.bits.to.bytes.seasons.controller;

import com.bits.to.bytes.seasons.service.SeasonsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:9005", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/seasons")
public class SeasonsController {

    Logger logger = LoggerFactory.getLogger(SeasonsController.class);

    @Autowired
    SeasonsService seasonsService;

    @GetMapping("/getSeasonsList")
    public ResponseEntity<List<Integer>> getSeasonsList(){

        logger.info("getting all the seasons");
        List<Integer> seasonsList= seasonsService.getSeasonsList();

        if(seasonsList != null){
            logger.info("response: "+seasonsList);
            return new ResponseEntity<>(seasonsList, HttpStatus.OK);
        }else{
            logger.info("seasons list is empty");
            return new ResponseEntity<>(seasonsList, HttpStatus.BAD_REQUEST);
        }
    }
}

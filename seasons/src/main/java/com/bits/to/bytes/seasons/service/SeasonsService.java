package com.bits.to.bytes.seasons.service;

import com.bits.to.bytes.seasons.util.ExtApiCalls;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeasonsService {

    @Autowired
    ExtApiCalls extApiCalls;

    Logger logger= LoggerFactory.getLogger(SeasonsService.class);

    public List<Integer> getSeasonsList(){
        logger.info("getting seasons List from static variable");
        List<Integer> seasonsList= ExtApiCalls.seasonsList;
        if(seasonsList == null){
            logger.info("seasons list is null in static variable so calling the ext api");
            extApiCalls.callSeasonsApi();
            seasonsList= ExtApiCalls.seasonsList;
        }

        return seasonsList;
    }
}

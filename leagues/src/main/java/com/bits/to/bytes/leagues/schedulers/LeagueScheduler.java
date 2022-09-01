package com.bits.to.bytes.leagues.schedulers;

import com.bits.to.bytes.leagues.model.League;
import com.bits.to.bytes.leagues.util.ExtAPICalls;
import com.bits.to.bytes.leagues.util.ExtAPICallsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LeagueScheduler {

    Logger logger= LoggerFactory.getLogger(ExtAPICallsImpl.class);
    @Autowired
    ExtAPICalls extAPICallsImpl;

    @Scheduled(cron = "0 0 0 * * *")
    public void updateLeagues(){
        logger.info("Scheduler to get leagues started");
        List<League> leagues = extAPICallsImpl.callLeagueAPI();
        logger.info("Scheduler to get leagues ended: leagues count: " + leagues.size());
    }
}

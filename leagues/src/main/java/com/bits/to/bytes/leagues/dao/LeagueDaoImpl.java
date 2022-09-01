package com.bits.to.bytes.leagues.dao;

import com.bits.to.bytes.leagues.dbmapper.LeagueDBMapper;
import com.bits.to.bytes.leagues.mapper.LeaguesObjectMapper;
import com.bits.to.bytes.leagues.model.League;
import com.bits.to.bytes.leagues.repository.LeagueRepository;
import com.bits.to.bytes.leagues.repository.SeasonRepository;
import com.bits.to.bytes.leagues.util.ExtAPICalls;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class LeagueDaoImpl {
    Logger logger = LoggerFactory.getLogger(LeagueDaoImpl.class);

    @Autowired
    LeagueRepository leagueRepository;
    @Autowired
    LeaguesObjectMapper leaguesObjectMapper;
    @Autowired
    SeasonRepository seasonRepository;


    public boolean saveOrUpdateLeagueAndSeasons(List<League> leagueList) {
        try {
            leagueRepository.deleteAll();
            seasonRepository.deleteAll();
            leagueList.forEach(league -> {
//                        League league= leagueList.get(0); -- created for testing
                LeagueDBMapper leagueDBMapper = leaguesObjectMapper.mapLeagueToDBLeague(league);
                leagueRepository.save(leagueDBMapper);
                seasonRepository.saveAll(league.getSeasons());
            });
            return true;
        } catch (Exception e) {
            logger.error("exceptiuon occured while saving objects");
            logger.error(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<LeagueDBMapper> getAllLeagues() {
        try{
            return leagueRepository.findAll();
        }catch (Exception e){
            logger.error("exception occured while getting leagues");
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
//        List<LeagueDBMapper> leagueDBMappers = null;
//        try{
//            logger.info("fetching leagues from DB");
//            leagueDBMappers= leagueRepository.findAll();
//            if(leagueDBMappers == null || leagueDBMappers.size()<1){
//                logger.info("Leagues not present in Db hence calling external api");
//                leagueDBMappers= extAPICalls.callLeagueAPI().stream()
//                        .map(league-> leaguesObjectMapper.mapLeagueToDBLeague(league)).collect(Collectors.toList());
//            }
//        }catch (Exception e){
//            logger.error("exception occured while getting leagues");
//            logger.error(e.getMessage());
//            e.printStackTrace();
//        }
//        return leagueDBMappers;
    }
}

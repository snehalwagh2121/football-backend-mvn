package com.bits.to.bytes.leagues.dao;

import com.bits.to.bytes.leagues.dbmapper.LeagueDBMapper;
import com.bits.to.bytes.leagues.repository.LeagueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LeagueDaoImpl {
    Logger logger = LoggerFactory.getLogger(LeagueDaoImpl.class);

    @Autowired
    LeagueRepository leagueRepository;


    public boolean saveOrUpdateLeagueAndSeasons(List<LeagueDBMapper> leagueList) {
        try {
            leagueRepository.deleteAll();
            leagueRepository.saveAll(leagueList);
            return true;
        } catch (Exception e) {
            logger.error("exception occured while saving objects");
            logger.error(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<LeagueDBMapper> getAllLeagues() {
        try {
            return leagueRepository.findAll();
        } catch (Exception e) {
            logger.error("exception occured while getting leagues");
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public List<LeagueDBMapper> getLeaguesBySeasonYear(int year) {
        try {
            return leagueRepository.findBySeasonYear(year);
        } catch (Exception e) {
            logger.error("exception occured while getting leagues");
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public boolean saveOrUpdateLeaguesForSeason(List<LeagueDBMapper> leagueList, int year) {
        try {

            leagueRepository.deleteBySeasonYear(year);
            leagueRepository.saveAll(leagueList);
            return true;
        } catch (Exception e) {
            logger.error("exception occured while saving objects");
            logger.error(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}

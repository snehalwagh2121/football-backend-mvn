package com.bits.to.bytes.teams.service;

//import com.bits.to.bytes.teams.dao.SeasonsDao;
import com.bits.to.bytes.teams.dao.TeamsDao;
import com.bits.to.bytes.teams.dbmapper.TeamsDBMapper;
import com.bits.to.bytes.teams.util.ExtAPICalls;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamsServiceImpl implements TeamsService {
    Logger logger = LoggerFactory.getLogger(TeamsServiceImpl.class);

    @Autowired
    TeamsDao teamsDao;
//    @Autowired
//    SeasonsDao seasonsDao;
    @Autowired
    ExtAPICalls extAPICalls;

    @Override
    public List<TeamsDBMapper> getTeamsList(int leagueId) {
        logger.info("teams service to get teams in a league start");
        try {
            List<TeamsDBMapper> teams = teamsDao.findTeamsinLeague(leagueId);
            if (teams.size() > 0) {
                logger.info("teams present in DB for league: " + leagueId);
                logger.info("teams service to get teams in a league end");
                return teams;
            } else {
                logger.info("teams NOT present in DB for league: " + leagueId + " so calling the online API");
                teams = extAPICalls.callteamsbyLeagueAPI(leagueId);
                logger.info("teams service to get teams in a league end");
                return teams;
            }
        } catch (Exception e) {
            logger.error("general exception");
            logger.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

//    @Override
//    public List<Integer> fetchTeamSeasons(int teamId) {
//        logger.info("teams service to get team seasons played");
//        try {
//            List<Integer> seasons = seasonsDao.findSeasonsForTeam(teamId);
//            if (seasons != null && seasons.size() > 0) {
//                logger.info("team seasons present in DB for team: " + teamId);
//                logger.info("teams service to get team seasons end");
//                return seasons;
//            } else {
//                logger.info("team Seasons NOT present in DB for team: " + teamId + " so calling the online API");
//                seasons = extAPICalls.callseasonsAPIforTeam(teamId);
//                logger.info("teams service to get teams in a league end");
//                return seasons;
//            }
//        } catch (Exception e) {
//            logger.error("general exception");
//            logger.error(e.getMessage());
//            e.printStackTrace();
//            return null;
//        }
//    }
}

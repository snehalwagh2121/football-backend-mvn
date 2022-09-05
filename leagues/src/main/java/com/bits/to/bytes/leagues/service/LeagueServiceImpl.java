package com.bits.to.bytes.leagues.service;

import com.bits.to.bytes.leagues.dao.LeagueDaoImpl;
import com.bits.to.bytes.leagues.dbmapper.LeagueDBMapper;
import com.bits.to.bytes.leagues.mapper.LeaguesObjectMapper;
import com.bits.to.bytes.leagues.util.ExtAPICalls;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeagueServiceImpl implements LeagueService {
    Logger logger = LoggerFactory.getLogger(LeagueServiceImpl.class);

    @Autowired
    LeagueDaoImpl leagueDao;
    @Autowired
    ExtAPICalls extAPICalls;
    @Autowired
    LeaguesObjectMapper leaguesObjectMapper;

    @Override
    public List<LeagueDBMapper> getLeagues() {
        List<LeagueDBMapper> leagueDBMappers = leagueDao.getAllLeagues();
        try {
            if (leagueDBMappers == null || leagueDBMappers.size() < 1) {
                logger.info("Leagues not present in Db hence calling external api");
                leagueDBMappers = extAPICalls.callLeagueAPI();
            }
        } catch (Exception e) {
            leagueDBMappers = null;
            logger.error("exception occured");
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return leagueDBMappers;
    }

    @Override
    public List<LeagueDBMapper> getLeaguesBySeason(int year) {
        logger.info("getting leagues for season: " + year);
        List<LeagueDBMapper> leaguesBySeason = leagueDao.getLeaguesBySeasonYear(year);
        try {
            if (leaguesBySeason == null || leaguesBySeason.size() < 1) {
                logger.info("leagues not present in DB for season: " + year);
                leaguesBySeason = extAPICalls.callLeagueBySeasonsAPI(year);
            }
        } catch (Exception e) {
            leaguesBySeason = null;
            logger.error("exception occured");
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return leaguesBySeason;
    }
}

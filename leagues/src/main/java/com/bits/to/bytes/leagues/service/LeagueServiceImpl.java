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
import java.util.stream.Collectors;

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
                leagueDBMappers = extAPICalls.callLeagueAPI().stream()
                        .map(league -> leaguesObjectMapper.mapLeagueToDBLeague(league)).collect(Collectors.toList());
            }
        } catch (Exception e) {
            leagueDBMappers = null;
            logger.error("exception occured");
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return leagueDBMappers;
    }
}

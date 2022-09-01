package com.bits.to.bytes.teams.dao;

import com.bits.to.bytes.teams.dbmapper.TeamsDBMapper;
import com.bits.to.bytes.teams.repository.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TeamsDao {

    @Autowired
    TeamsRepository teamsRepository;

    public List<TeamsDBMapper> findTeamsinLeague(int leagueId) {
        return teamsRepository.findByLeagueIdFK(leagueId);
    }

}

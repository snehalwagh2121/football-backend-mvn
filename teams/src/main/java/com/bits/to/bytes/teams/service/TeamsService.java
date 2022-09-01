package com.bits.to.bytes.teams.service;

import com.bits.to.bytes.teams.dbmapper.TeamsDBMapper;

import java.util.List;

public interface TeamsService {
    List<TeamsDBMapper> getTeamsList(int leagueId);

//    List<Integer> fetchTeamSeasons(int teamId);
}

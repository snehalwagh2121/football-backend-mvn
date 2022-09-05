package com.bits.to.bytes.leagues.service;

import com.bits.to.bytes.leagues.dbmapper.LeagueDBMapper;

import java.util.List;

public interface LeagueService {
    List<LeagueDBMapper> getLeagues();

    List<LeagueDBMapper> getLeaguesBySeason(int year);
}

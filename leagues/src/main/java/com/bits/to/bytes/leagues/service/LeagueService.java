package com.bits.to.bytes.leagues.service;

import com.bits.to.bytes.leagues.dbmapper.LeagueDBMapper;
import com.bits.to.bytes.leagues.model.League;

import java.util.List;

public interface LeagueService {
    List<LeagueDBMapper> getLeagues();
}

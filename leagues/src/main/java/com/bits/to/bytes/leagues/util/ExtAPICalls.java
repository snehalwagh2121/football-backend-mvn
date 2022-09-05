package com.bits.to.bytes.leagues.util;

import com.bits.to.bytes.leagues.dbmapper.LeagueDBMapper;
import com.bits.to.bytes.leagues.model.League;

import java.util.List;

public interface ExtAPICalls {
    List<LeagueDBMapper> callLeagueAPI();

    List<LeagueDBMapper> callLeagueBySeasonsAPI(int year);
}

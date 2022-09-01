package com.bits.to.bytes.leagues.service;

import com.bits.to.bytes.leagues.dbmapper.SeasonsDBMapper;

import java.util.List;

public interface SeasonService {
    List<SeasonsDBMapper> fetchSeasons(int leagueId);
}

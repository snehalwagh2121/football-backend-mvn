package com.bits.to.bytes.leagues.service;

import com.bits.to.bytes.leagues.dao.SeasonsDaoImpl;
import com.bits.to.bytes.leagues.dbmapper.SeasonsDBMapper;
import com.bits.to.bytes.leagues.repository.SeasonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeasonsServiceImpl implements SeasonService{

    @Autowired
    SeasonRepository repository;

    @Override
    public List<SeasonsDBMapper> fetchSeasons(int leagueId) {
        return repository.findByLeagueIdFK(leagueId);
    }
}

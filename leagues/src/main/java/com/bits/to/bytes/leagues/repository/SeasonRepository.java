package com.bits.to.bytes.leagues.repository;

import com.bits.to.bytes.leagues.dbmapper.SeasonsDBMapper;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeasonRepository extends MongoRepository<SeasonsDBMapper, String> {
    List<SeasonsDBMapper> findByLeagueIdFK(int leagueId);
}

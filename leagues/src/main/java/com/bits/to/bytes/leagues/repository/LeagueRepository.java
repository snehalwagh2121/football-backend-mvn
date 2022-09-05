package com.bits.to.bytes.leagues.repository;

import com.bits.to.bytes.leagues.dbmapper.LeagueDBMapper;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeagueRepository extends MongoRepository<LeagueDBMapper, String> {
    @Query("{'year': ?0 }")
    List<LeagueDBMapper> findBySeasonYear(int year);

    void deleteBySeasonYear(int year);
}

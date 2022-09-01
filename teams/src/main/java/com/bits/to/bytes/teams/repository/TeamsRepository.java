package com.bits.to.bytes.teams.repository;

import com.bits.to.bytes.teams.dbmapper.TeamsDBMapper;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamsRepository extends MongoRepository<TeamsDBMapper, String> {
    List<TeamsDBMapper> findByLeagueIdFK(int leagueId);
}

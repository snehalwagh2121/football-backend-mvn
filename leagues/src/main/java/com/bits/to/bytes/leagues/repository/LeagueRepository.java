package com.bits.to.bytes.leagues.repository;

import com.bits.to.bytes.leagues.dbmapper.LeagueDBMapper;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeagueRepository extends MongoRepository<LeagueDBMapper, String> {
}

package com.bits.to.bytes.players.repository;

import com.bits.to.bytes.players.dbmapper.PlayersDbMapper;
import com.bits.to.bytes.players.model.Players;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayersRepository extends MongoRepository<PlayersDbMapper, String> {
    List<Players> findByteamIdAndSeason(int teamId, int season);
}

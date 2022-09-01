package com.bits.to.bytes.players.dao;

import com.bits.to.bytes.players.dbmapper.PlayersDbMapper;
import com.bits.to.bytes.players.model.Players;
import com.bits.to.bytes.players.objectMapper.PlayersObjectMapper;
import com.bits.to.bytes.players.repository.PlayersRepository;
import com.bits.to.bytes.players.util.ExtAPICall;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlayersDaoImpl implements PlayersDao{
    Logger logger= LoggerFactory.getLogger(PlayersDaoImpl.class);

    @Autowired
    PlayersRepository playersRepository;
    @Autowired
    ExtAPICall extAPICall;
    @Autowired
    PlayersObjectMapper playersObjectMapper;

    @Override
    public List<Players> getPlayersList(int teamId, int season) {
        logger.info("getting players list dao");
        try{
            List<Players> playersList= playersRepository.findByteamIdAndSeason(teamId, season);
            if(playersList!=null && playersList.size()>0){
                logger.info("players found in DB hence returning back");
                return playersList;
            }
            logger.info("players not found in db so calling external API");
            playersList= extAPICall.getPlayersByteamAndSeason(teamId, season);
            if(playersList!=null){
                logger.info("saving players in db");
                List<PlayersDbMapper> playersDbMapper= playersObjectMapper.mapPlayersToDBObj(playersList, teamId, season);
                playersRepository.saveAll(playersDbMapper);
                logger.info("successfully saved players in DB");
                return playersList;
            }
            return null;
        }catch (Exception e){
            logger.error("Exception occured");
            logger.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}

package com.bits.to.bytes.players.service;

import com.bits.to.bytes.players.dao.PlayersDao;
import com.bits.to.bytes.players.model.Players;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayersServiceImpl implements PlayersService{

    @Autowired
    PlayersDao playersDao;

    @Override
    public List<Players> getPlayers(int teamId, int season) {
        List<Players> playersList= playersDao.getPlayersList(teamId, season);
        return playersList;
    }
}

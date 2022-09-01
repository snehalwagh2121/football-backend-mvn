package com.bits.to.bytes.players.dao;

import com.bits.to.bytes.players.model.Players;

import java.util.List;

public interface PlayersDao {
    List<Players> getPlayersList(int teamId, int season);
}

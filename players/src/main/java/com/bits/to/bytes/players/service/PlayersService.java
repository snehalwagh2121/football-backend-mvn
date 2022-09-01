package com.bits.to.bytes.players.service;

import com.bits.to.bytes.players.model.Players;

import java.util.List;

public interface PlayersService {
    List<Players> getPlayers(int teamId, int season);
}

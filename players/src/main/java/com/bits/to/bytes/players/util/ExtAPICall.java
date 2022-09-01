package com.bits.to.bytes.players.util;

import com.bits.to.bytes.players.model.Players;

import java.util.List;

public interface ExtAPICall {
    List<Players> getPlayersByteamAndSeason(int teamId, int season);
}

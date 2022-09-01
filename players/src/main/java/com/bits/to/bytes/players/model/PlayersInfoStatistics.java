package com.bits.to.bytes.players.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Setter
@Getter
public class PlayersInfoStatistics {
    Players player;
    List<Object> statistics;
}

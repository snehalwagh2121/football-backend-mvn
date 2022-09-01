package com.bits.to.bytes.players.objectMapper;

import com.bits.to.bytes.players.dbmapper.PlayersDbMapper;
import com.bits.to.bytes.players.model.Players;
import com.bits.to.bytes.players.model.PlayersApiResponse;
import com.bits.to.bytes.players.model.PlayersInfoStatistics;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlayersObjectMapper {
    public List<Players> mapPlayers(PlayersApiResponse playersApiResponse, int teamId, int season) {

        List<Players> playersList= playersApiResponse.getResponse().stream()
                .map(res -> {
                    Players p= Players.builder()
                            .age(res.getPlayer().getAge())
                            .birth(res.getPlayer().getBirth())
                            .id(res.getPlayer().getId())
                            .firstname(res.getPlayer().getFirstname())
                            .lastname(res.getPlayer().getLastname())
                            .name(res.getPlayer().getName())
                            .nationality(res.getPlayer().getNationality())
                            .build();
                    return p;
                }).collect(Collectors.toList());

        return playersList;
    }

    public List<PlayersDbMapper> mapPlayersToDBObj(List<Players> playersList, int teamId, int season) {
        return playersList.stream()
                .map(players -> {
                    PlayersDbMapper playersDbMapper = PlayersDbMapper.builder()
                            .age(players.getAge())
                            .birth(players.getBirth())
                            .id(players.getId())
                            .firstname(players.getFirstname())
                            .lastname(players.getLastname())
                            .name(players.getName())
                            .nationality(players.getNationality())
                            .teamId(teamId)
                            .season(season)
                            .build();
                    return playersDbMapper;
                }).collect(Collectors.toList());
    }
}

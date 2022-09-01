package com.bits.to.bytes.players.util;

import com.bits.to.bytes.players.model.Players;
import com.bits.to.bytes.players.model.PlayersApiResponse;
import com.bits.to.bytes.players.objectMapper.PlayersObjectMapper;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class ExtAPICallImpl implements ExtAPICall{
    Logger logger= LoggerFactory.getLogger(ExtAPICallImpl.class);

    @Autowired
    WebClient.Builder webClientBuilder;
    @Autowired
    PlayersObjectMapper playersObjectMapper;

    @Value("${players.base.url}")
    String playersBaseUrl;
    @Value("${players.path}")
    String playersPath;
    @Value("${rapidAPIHost}")
    String rapidAPIHost;
    @Value("${rapidAPIKey}")
    String rapidAPIKey;

    Gson gson= new Gson();

    @Override
    public List<Players> getPlayersByteamAndSeason(int teamId, int season) {
        logger.info("calling ext api");
        String playersResponse= webClientBuilder.baseUrl(playersBaseUrl).build()
                .get()
                .uri(uriBuilder -> uriBuilder
                .path(playersPath)
                .queryParam("team", teamId)
                .queryParam("season", season)
                .build())
                .header("X-RapidAPI-Host", rapidAPIHost)
                .header("X-RapidAPI-Key", rapidAPIKey)
                .retrieve()
                .bodyToFlux(String.class)
                .blockLast();
        logger.info("api response: "+playersResponse);
        PlayersApiResponse playersApiResponse= gson.fromJson(playersResponse, PlayersApiResponse.class);
        List<Players> playersList= playersObjectMapper.mapPlayers(playersApiResponse, teamId, season);
        logger.info("players listt: "+gson.toJson(playersList));
        return playersList;

    }
}

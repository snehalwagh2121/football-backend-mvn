package com.bits.to.bytes.leagues.util;

import com.bits.to.bytes.leagues.dao.LeagueDaoImpl;
import com.bits.to.bytes.leagues.mapper.LeaguesObjectMapper;
import com.bits.to.bytes.leagues.model.League;
import com.bits.to.bytes.leagues.model.LeaguesListResponse;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExtAPICallsImpl implements ExtAPICalls {
    Logger logger = LoggerFactory.getLogger(ExtAPICallsImpl.class);

    @Value("${leagueAPIUrl}")
    String leagueAPIUrl;
    @Value("${rapidAPIHost}")
    String rapidAPIHost;
    @Value("${rapidAPIKey}")
    String rapidAPIKey;

    @Autowired
    WebClient.Builder webClientBuilder;
    @Autowired
    LeaguesObjectMapper leaguesObjectMapper;
    @Autowired
    LeagueDaoImpl leagueDao;

    Gson gson = new Gson();

    @Override
    public List<League> callLeagueAPI() {
        logger.info("Calling the external api with WEBClient");
        List<League> leagueList = new ArrayList<>();
        try {
            String response = webClientBuilder.build()
                    .get()
                    .uri(leagueAPIUrl)
                    .header("X-RapidAPI-Host", rapidAPIHost)
                    .header("X-RapidAPI-Key", rapidAPIKey)
                    .retrieve()
                    .bodyToFlux(String.class)
                    .blockLast();
            LeaguesListResponse responseMapped = gson.fromJson(response, LeaguesListResponse.class);
            leagueList = leaguesObjectMapper.leaguesResponseMapper(responseMapped);
            logger.info("league mapped now saving records in db");
            leagueDao.saveOrUpdateLeagueAndSeasons(leagueList);
            logger.info("League successfully saved in DB");
        } catch (ResourceAccessException e) {
            logger.error("Resource access exception");
            logger.info(e.getMessage());
        } catch (Exception e) {
            logger.error("General exception");
            logger.info(e.getMessage());
            e.printStackTrace();
        }
        logger.info("Called the external api to get leagues info.");
        return leagueList;
    }
}

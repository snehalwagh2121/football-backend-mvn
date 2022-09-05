package com.bits.to.bytes.leagues.util;

import com.bits.to.bytes.leagues.dao.LeagueDaoImpl;
import com.bits.to.bytes.leagues.dbmapper.LeagueDBMapper;
import com.bits.to.bytes.leagues.mapper.LeaguesObjectMapper;
import com.bits.to.bytes.leagues.model.LeaguesBySeasonResponse;
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
    @Value("${seasons.league.base.url}")
    String leagueBySeasonBaseUrl;
    @Value("${seasons.league.path}")
    String leagueBySeasonPath;
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
    public List<LeagueDBMapper> callLeagueAPI() {
        logger.info("Calling the external api with WEBClient");
        List<LeagueDBMapper> leagueList = new ArrayList<>();
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
            leagueList = leaguesObjectMapper.leaguesResponseMapper(responseMapped.getResponse());
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

    @Override
    public List<LeagueDBMapper> callLeagueBySeasonsAPI(int year) {
        logger.info("calling leagues by seasons API for season: "+year);
        List<LeagueDBMapper> leagueList = new ArrayList<>();
        try {
            String response = webClientBuilder.baseUrl(leagueBySeasonBaseUrl).build()
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path(leagueBySeasonPath)
                            .queryParam("season", year)
                            .build())
                    .header("X-RapidAPI-Host", rapidAPIHost)
                    .header("X-RapidAPI-Key", rapidAPIKey)
                    .retrieve()
                    .bodyToFlux(String.class)
                    .blockLast();
            logger.info("response received: "+response);
            LeaguesBySeasonResponse responseMapped = gson.fromJson(response, LeaguesBySeasonResponse.class);
            leagueList = leaguesObjectMapper.leaguesResponseMapper(responseMapped.getResponse());
            logger.info("league mapped now saving records in db");
            boolean isSuccess = leagueDao.saveOrUpdateLeaguesForSeason(leagueList, year);
            if (isSuccess)
                logger.info("League successfully saved in DB");
            else {
                logger.info("Something went wrong while saving the leagues for season : " + year + " in DB ");
                throw new Exception("DB exception occured");
            }
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

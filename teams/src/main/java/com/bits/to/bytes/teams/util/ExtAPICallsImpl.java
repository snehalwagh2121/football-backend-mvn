package com.bits.to.bytes.teams.util;

//import com.bits.to.bytes.teams.dao.SeasonsDao;
import com.bits.to.bytes.teams.dbmapper.TeamsDBMapper;
//import com.bits.to.bytes.teams.model.SeasonsApiResponse;
import com.bits.to.bytes.teams.model.TeamsAPIResponse;
import com.bits.to.bytes.teams.objectmapper.TeamsObjectMapper;
import com.bits.to.bytes.teams.repository.TeamsRepository;
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

    @Value("${teamsAPIUrl}")
    String teamsAPIUrl;
//    @Value("${teamSeasonsAPIUrl}")
//    String teamSeasonsAPIUrl;
    @Value("${rapidAPIHost}")
    String rapidAPIHost;
    @Value("${rapidAPIKey}")
    String rapidAPIKey;

    @Autowired
    WebClient.Builder webClientBuilder;
    @Autowired
    TeamsObjectMapper teamsObjectMapper;
    @Autowired
    TeamsRepository teamsRepository;
//    @Autowired
//    SeasonsDao seasonsDao;


    Gson gson = new Gson();

    @Override
    public List<TeamsDBMapper> callteamsbyLeagueAPI(int leagueId) {
        logger.info("Calling the external api with WEBClient");
        List<TeamsDBMapper> teamsDBMappers = new ArrayList<>();
        try {
            String response = webClientBuilder.build()
                    .get()
                    .uri(teamsAPIUrl + leagueId)
                    .header("X-RapidAPI-Host", rapidAPIHost)
                    .header("X-RapidAPI-Key", rapidAPIKey)
                    .retrieve()
                    .bodyToFlux(String.class)
                    .blockLast();
            TeamsAPIResponse responseMapped = gson.fromJson(response, TeamsAPIResponse.class);
            if (responseMapped.getApi().getResults() > 0) {
                teamsDBMappers = teamsObjectMapper.teamsResponseMapper(responseMapped, leagueId);
                logger.info("team mapped now saving records in db");
                teamsRepository.saveAll(teamsDBMappers);
                logger.info("teams successfully saved in DB");
            } else {
                teamsDBMappers = null;
                logger.info("teams not present for league : " + leagueId);
                if (responseMapped.getApi().getError() != null)
                    logger.error("ERROR from API: " + responseMapped.getApi().getError());
            }
        } catch (ResourceAccessException e) {
            logger.error("Resource access exception");
            logger.info(e.getMessage());
        } catch (Exception e) {
            logger.error("General exception");
            logger.info(e.getMessage());
            e.printStackTrace();
        }
        logger.info("Called the external api to get teams info.");
        return teamsDBMappers;
    }

//    @Override
//    public List<Integer> callseasonsAPIforTeam(int teamId) {
//        logger.info("Calling the external api with WEBClient to get team seasons");
//        List<Integer> seasonsList = new ArrayList<>();
//        try {
//            String response = webClientBuilder.baseUrl("https://api-football-v1.p.rapidapi.com").build()
//                    .get()
//                    .uri(uriBuilder -> uriBuilder
//                            .path("/v3/teams/seasons")
//                            .queryParam("team", teamId)
//                            .build())
//                    .header("X-RapidAPI-Host", rapidAPIHost)
//                    .header("X-RapidAPI-Key", rapidAPIKey)
//                    .retrieve()
//                    .bodyToFlux(String.class)
//                    .blockLast();
//            logger.info("seasons response" + response);
//            SeasonsApiResponse responseMapped = gson.fromJson(response, SeasonsApiResponse.class);
//            seasonsList = responseMapped.getResponse();
//            logger.info("seasons present now saving records in db");
//            boolean isSaved = seasonsDao.saveSeasons(seasonsList, teamId);
//            if (isSaved) {
//                logger.info("seasons successfully saved in DB");
//            } else {
//                logger.info("seasons successfully saved in DB");
//            }
//        } catch (ResourceAccessException e) {
//            logger.error("Resource access exception");
//            logger.info(e.getMessage());
//        } catch (Exception e) {
//            logger.error("General exception");
//            logger.info(e.getMessage());
//            e.printStackTrace();
//        }
//        logger.info("Called the external api to get teams info.");
//        return seasonsList;
//    }
}

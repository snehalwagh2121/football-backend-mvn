package com.bits.to.bytes.seasons.util;


import com.bits.to.bytes.seasons.model.SeasonsApiResponse;
import com.google.gson.Gson;
import lombok.extern.flogger.Flogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
public class ExtApiCalls {

    Logger logger= LoggerFactory.getLogger(ExtApiCalls.class);
    @Value("${seasons.url}")
    String seasonsUrl;
    @Value("${rapidAPIHost}")
    String rapidAPIHost;
    @Value("${rapidAPIKey}")
    String rapidAPIKey;

    @Autowired
    WebClient.Builder webClientBuilder;

    Gson gson = new Gson();

    public static List<Integer> seasonsList;

    public void callSeasonsApi(){
        logger.info("calling ext api to get seasons");
        try {
            String seasonsResponse= webClientBuilder.build()
                    .get()
                    .uri(seasonsUrl)
                    .header("X-RapidAPI-Host", rapidAPIHost)
                    .header("X-RapidAPI-Key", rapidAPIKey)
                    .retrieve()
                    .bodyToFlux(String.class)
                    .blockLast();
            logger.info("seasonsResponse: "+ seasonsResponse);
            SeasonsApiResponse seasonsApiResponse= gson.fromJson(seasonsResponse, SeasonsApiResponse.class);
            if(seasonsApiResponse.getResponse() == null || seasonsApiResponse.getResponse().size() <= 0){
                logger.info("list is empty.");
            }
            seasonsList = seasonsApiResponse.getResponse();
        } catch (ResourceAccessException e) {
            logger.error("Resource access exception");
            logger.info(e.getMessage());
        } catch (Exception e) {
            logger.error("General exception");
            logger.info(e.getMessage());
            e.printStackTrace();
        }
        logger.info("Called the external api to get seasons list.");
    }
}

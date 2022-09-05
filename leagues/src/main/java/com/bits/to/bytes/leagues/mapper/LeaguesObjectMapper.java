package com.bits.to.bytes.leagues.mapper;

import com.bits.to.bytes.leagues.dbmapper.LeagueDBMapper;
import com.bits.to.bytes.leagues.model.League;
import com.bits.to.bytes.leagues.model.LeagueInteralResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LeaguesObjectMapper {
    public List<LeagueDBMapper> leaguesResponseMapper(List<LeagueInteralResponse> leaguesRes) {
        List<LeagueDBMapper> leagueList = new ArrayList<>();
        leaguesRes.forEach(res -> {
            res.getSeasons().stream()
                    .map(season -> {
                        LeagueDBMapper l = LeagueDBMapper.builder()
                                .leagueId(res.getLeague().getId())
                                .name(res.getLeague().getName())
                                .type(res.getLeague().getType())
                                .country(res.getCountry().getName())
                                .seasonYear(season.getYear())
                                .build();
                        return l;
                    }).collect(Collectors.toCollection(() -> leagueList));
        });
        return leagueList;
    }


    public List<LeagueDBMapper> mapLeagueToDBLeague(League league) {

        List<LeagueDBMapper> leaguesList = league.getSeasons()
                .stream()
                .map(seasons -> {
                    LeagueDBMapper leagueDBObj = LeagueDBMapper.builder()
                            .leagueId(league.getLeagueId())
                            .name(league.getName())
                            .country(league.getCountry())
                            .type(league.getType())
                            .seasonYear(seasons.getYear())
                            .build();
                    return leagueDBObj;
                }).collect(Collectors.toList());

        return leaguesList;

    }
}

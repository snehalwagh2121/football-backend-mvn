package com.bits.to.bytes.leagues.mapper;

import com.bits.to.bytes.leagues.dbmapper.LeagueDBMapper;
import com.bits.to.bytes.leagues.dbmapper.SeasonsDBMapper;
import com.bits.to.bytes.leagues.model.League;
import com.bits.to.bytes.leagues.model.LeaguesListResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LeaguesObjectMapper {
    public List<League> leaguesResponseMapper(LeaguesListResponse responseMapped) {

        List<League> leagueList = responseMapped.getResponse().stream()
                .map(leagues -> {
                    League l = League.builder()
                            .leagueId(leagues.getLeague().getId())
                            .name(leagues.getLeague().getName())
                            .type(leagues.getLeague().getType())
                            .country(leagues.getCountry().getName())
                            .seasons(leagues.getSeasons()
                                    .stream()
                                    .map(seasonsList -> {
                                        SeasonsDBMapper s = SeasonsDBMapper.builder()
                                                .year(seasonsList.getYear())
                                                .start(seasonsList.getStart())
                                                .end(seasonsList.getEnd())
                                                .current(seasonsList.isCurrent())
                                                .leagueIdFK(leagues.getLeague().getId())
                                                .build();
                                        return s;
                                    }).collect(Collectors.toList()))
                            .build();
                    return l;
                }).collect(Collectors.toList());

        return leagueList;
    }

    public LeagueDBMapper mapLeagueToDBLeague(League league) {
        return LeagueDBMapper.builder()
                .leagueId(league.getLeagueId())
                .country(league.getCountry())
                .name(league.getName())
                .type(league.getType())
                .build();

    }
}

package com.bits.to.bytes.teams.objectmapper;

import com.bits.to.bytes.teams.dbmapper.TeamsDBMapper;
import com.bits.to.bytes.teams.model.TeamsAPIResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeamsObjectMapper {

    public List<TeamsDBMapper> teamsResponseMapper(TeamsAPIResponse responseMapped, int leagueId) {
        return responseMapped.getApi().getTeams()
                .stream()
                .map(team -> {
                    TeamsDBMapper teamsDBMapper = TeamsDBMapper.builder()
                            .code(team.getCode())
                            .country(team.getCountry())
                            .founded(team.getFounded())
                            .is_national(team.is_national())
                            .leagueIdFK(leagueId)
                            .logo(team.getLogo())
                            .team_id(team.getTeam_id())
                            .name(team.getName())
                            .venue_address(team.getVenue_address())
                            .venue_capacity(team.getVenue_capacity())
                            .venue_city(team.getVenue_city())
                            .venue_name(team.getVenue_name())
                            .venue_surface(team.getVenue_surface())
                            .build();
                    return teamsDBMapper;
                }).collect(Collectors.toList());

    }
}

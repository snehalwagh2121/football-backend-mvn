package com.bits.to.bytes.teams.dbmapper;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Getter
@Setter
@Document(collection = "teams")
public class TeamsDBMapper {
    int leagueIdFK;
    int team_id;
    String name;
    String code;
    String logo;
    String country;
    boolean is_national;
    int founded;
    String venue_name;
    String venue_surface;
    String venue_address;
    String venue_city;
    int venue_capacity;

}

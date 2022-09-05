package com.bits.to.bytes.leagues.dbmapper;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "leagues")
@Builder
@Getter
@Setter
public class LeagueDBMapper {
    private int leagueId;
    private String name;
    private String type;
    private String country;
    private int seasonYear;
}

package com.bits.to.bytes.leagues.model;

import com.bits.to.bytes.leagues.dbmapper.SeasonsDBMapper;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
public class League {
    private int leagueId;
    private String name;
    private String type;
    private String country;
    private List<SeasonsDBMapper> seasons;
}

package com.bits.to.bytes.leagues.model;

import lombok.*;

@Builder
@Getter
@Setter
public class League {
    private int leagueId;
    private String name;
    private String type;
    private String country;
}

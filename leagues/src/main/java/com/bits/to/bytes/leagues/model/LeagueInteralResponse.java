package com.bits.to.bytes.leagues.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
public class LeagueInteralResponse {
    InternalLeague league;
    Country country;
    List<InternalSeasons> seasons;
}

package com.bits.to.bytes.leagues.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaguesBySeasonResponse {
    String get;
    Object parameters;
    Object[] errors;
    int results;
    Object paging;
    List<LeagueInteralResponse> response;
}

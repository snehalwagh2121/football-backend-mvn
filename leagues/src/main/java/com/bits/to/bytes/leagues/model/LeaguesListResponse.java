package com.bits.to.bytes.leagues.model;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeaguesListResponse {
    String get;
    Object[] parameters;
    Object[] errors;
    int results;
    Object paging;
    List<LeagueInteralResponse> response;
}

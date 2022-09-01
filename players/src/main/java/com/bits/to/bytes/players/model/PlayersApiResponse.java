package com.bits.to.bytes.players.model;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayersApiResponse {
    String get;
    Object parameters;
    Object errors;
    int result;
    Object paging;
    List<PlayersInfoStatistics> response;
}

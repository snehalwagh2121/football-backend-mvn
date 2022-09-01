package com.bits.to.bytes.teams.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
public class TeamsAPIOuter {
    int results;
    String error;
    List<TeamsInternal> teams;
}

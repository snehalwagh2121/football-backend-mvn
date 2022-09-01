package com.bits.to.bytes.teams.util;

import com.bits.to.bytes.teams.dbmapper.TeamsDBMapper;
import com.bits.to.bytes.teams.model.TeamsAPIResponse;
import org.apache.catalina.LifecycleState;

import java.util.List;

public interface ExtAPICalls {
    List<TeamsDBMapper> callteamsbyLeagueAPI(int leagueId);

//    List<Integer> callseasonsAPIforTeam(int teamId);
}

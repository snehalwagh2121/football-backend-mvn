package com.bits.to.bytes.leagues.dbmapper;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Builder
@Getter
@Setter
@Document(collection = "seasons")
public class SeasonsDBMapper {
    private int year;
    private Date start;
    private Date end;
    private boolean current;
    private int leagueIdFK;
}

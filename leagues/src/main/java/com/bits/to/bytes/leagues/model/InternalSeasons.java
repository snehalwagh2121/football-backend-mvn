package com.bits.to.bytes.leagues.model;

import lombok.*;

import java.util.Date;

@Data
public class InternalSeasons {
    int year;
    Date start;
    Date end;
    boolean current;
    Object coverage;
}

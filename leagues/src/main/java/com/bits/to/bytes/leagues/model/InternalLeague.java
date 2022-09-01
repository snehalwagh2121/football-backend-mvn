package com.bits.to.bytes.leagues.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class InternalLeague {
    int id;
    String name;
    String type;
    String logo;
}

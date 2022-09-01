package com.bits.to.bytes.players.model;

import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DOB {
    Date date;
    String place;
    String country;
}

package com.bits.to.bytes.players.model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Players {
    int id;
    String name;
    String firstname;
    String lastname;
    int age;
    DOB birth;
    String nationality;

}

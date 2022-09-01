package com.bits.to.bytes.players.dbmapper;

import com.bits.to.bytes.players.model.DOB;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "players")
public class PlayersDbMapper {
    int id;
    String name;
    String firstname;
    String lastname;
    int age;
    DOB birth;
    String nationality;
    int season;
    int teamId;
}

package com.bits.to.bytes.teams.model;

import lombok.*;
import org.springframework.stereotype.Service;

@Builder
@Getter
@Service
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeamsInternal {
    int team_id;
    String name;
    String code;
    String logo;
    String country;
    boolean is_national;
    int founded;
    String venue_name;
    String venue_surface;
    String venue_address;
    String venue_city;
    int venue_capacity;
}

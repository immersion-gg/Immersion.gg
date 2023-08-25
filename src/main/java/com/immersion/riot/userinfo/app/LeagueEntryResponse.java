package com.immersion.riot.userinfo.app;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LeagueEntryResponse {
    String queueType;
    String rank;
    String tier;
    int leaguePoints;
}

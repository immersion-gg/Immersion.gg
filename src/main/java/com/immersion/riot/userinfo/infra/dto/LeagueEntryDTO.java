package com.immersion.riot.userinfo.infra.dto;

public record LeagueEntryDTO(
    String queueType,
    String tier,
    String rank,
    String summonerId,
    String summonerName,
    int leaguePoints
) {

}

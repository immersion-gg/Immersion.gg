package com.immersion.riot.userinfo.infra.dto;

public record SummonerDTO(
        int profileIconId,
        String name,
        String id,
        String puuid,
        long summonerLevel
) {

}

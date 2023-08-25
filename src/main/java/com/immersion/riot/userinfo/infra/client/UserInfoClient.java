package com.immersion.riot.userinfo.infra.client;

import com.immersion.riot.config.RiotFeignConfiguration;
import com.immersion.riot.userinfo.infra.dto.LeagueEntryDTO;
import com.immersion.riot.userinfo.infra.dto.SummonerDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "summoner", configuration = RiotFeignConfiguration.class, url = "${riot.api.url.kr}")
public interface UserInfoClient {
    @GetMapping("/lol/summoner/v4/summoners/by-name/{summonerName}")
    SummonerDTO getSummoner(@PathVariable String summonerName);

    @GetMapping("/lol/league/v4/entries/by-summoner/{encryptedSummonerId}")
    List<LeagueEntryDTO> getSummonerLeague(@PathVariable String encryptedSummonerId);
}

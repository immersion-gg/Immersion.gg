package com.immersion.riot.spectator.infra;

import com.immersion.riot.config.RiotFeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "spectator", configuration = RiotFeignConfiguration.class, url = "${riot.api.url.kr}")

public interface SpectatorClient {
    @GetMapping("/lol/spectator/v4/active-games/by-summoner/{encryptedSummonerId}")
    Optional<SpectatorQueryDTO> getSpectator(@PathVariable String encryptedSummonerId);
}

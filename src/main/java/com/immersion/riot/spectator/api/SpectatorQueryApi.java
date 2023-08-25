package com.immersion.riot.spectator.api;

import com.immersion.riot.spectator.app.SpectatorQueryResponse;
import com.immersion.riot.spectator.app.SpectatorQueryService;
import com.immersion.riot.common.app.NoDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SpectatorQueryApi {
    private final SpectatorQueryService spectatorQueryService;

    @GetMapping("/spectator/{summonerId}")
    public ResponseEntity<SpectatorQueryResponse> getSpectator(@PathVariable String summonerId) {
        try {
            SpectatorQueryResponse response = spectatorQueryService.getSpectator(summonerId);
            return ResponseEntity.ok(response);
        } catch (NoDataException e) {
            return ResponseEntity.noContent().build();
        }
    }
}

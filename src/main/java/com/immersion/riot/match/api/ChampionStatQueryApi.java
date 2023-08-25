package com.immersion.riot.match.api;

import com.immersion.riot.common.app.NoDataException;
import com.immersion.riot.match.app.dto.ChampionStatResponse;
import com.immersion.riot.match.query.ChampionStatQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ChampionStatQueryApi {

    private final ChampionStatQueryService championStatQueryService;

    @GetMapping("/match/stat/{puuid}")
    public ResponseEntity<List<ChampionStatResponse>> getChampionStatsByPuuid(@PathVariable String puuid) {
        try {
            List<ChampionStatResponse> championStatResponses = championStatQueryService.getMostChampionByPuuid(puuid);

            return ResponseEntity.ok(championStatResponses);
        } catch (NoDataException e){
            return ResponseEntity.noContent().build();
        }
    }
}


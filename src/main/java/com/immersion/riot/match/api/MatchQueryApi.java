package com.immersion.riot.match.api;

import com.immersion.riot.match.app.dto.*;
import com.immersion.riot.match.app.service.WinRateAnalysisService;
import com.immersion.riot.match.query.MatchQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MatchQueryApi {

    private final MatchQueryService matchQueryService;
    private final WinRateAnalysisService winRateAnalysisService;

    @GetMapping("/match/{puuid}")
    public ResponseEntity<Slice<MatchResponse>> getMatchListByPuuid(
            @PathVariable String puuid,
            @PageableDefault(sort = "gameEndTime", direction = Sort.Direction.DESC) Pageable pageable) {

        Slice<MatchResponse> matchList = matchQueryService.getMatchList(puuid, pageable);

        return ResponseEntity.ok(matchList);
    }

    @GetMapping("/summoners/{summonerName}")
    public ResponseEntity<MatchWithStatsResponse> searchBySummoner(
            @PathVariable String summonerName,
            @PageableDefault(sort = "gameEndTime", direction = Sort.Direction.DESC) Pageable pageable) {

        MatchWithStatsResponse matchWithStatsResponse = matchQueryService.searchBySummoner(summonerName, pageable);
        return ResponseEntity.ok(matchWithStatsResponse);
    }

    @GetMapping("/match/win-rate/{puuid}/{championName}")
    public Map<String, ChampionWinRateResponse> analyzeWinRate(@PathVariable String puuid, @PathVariable String championName) {
        return winRateAnalysisService.getAnalyzedWinRate(puuid, championName);
    }

}

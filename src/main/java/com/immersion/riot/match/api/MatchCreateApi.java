package com.immersion.riot.match.api;

import com.immersion.riot.match.app.service.MatchCreateService;
import com.immersion.riot.match.domain.entity.Match;
import com.immersion.riot.match.domain.repository.MatchRepository;
import com.immersion.riot.match.infra.service.RiotMatchCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MatchCreateApi {

    private final MatchCreateService matchCreateService;

    @PostMapping("/match/{puuid}")
    public ResponseEntity<String> updateMatchHistory(@PathVariable String puuid) {
        matchCreateService.saveMatchList(puuid);
        return ResponseEntity.ok("save complete!");
    }

}

package com.immersion.riot.userinfo.api;

import com.immersion.riot.userinfo.app.LeagueEntryResponse;
import com.immersion.riot.userinfo.app.UserInfoResponse;
import com.immersion.riot.userinfo.app.UserInfoService;
import com.immersion.riot.userinfo.app.UserRankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserInfoApi {

    private final UserInfoService userInfoService;
    private final UserRankService userRankService;

    @GetMapping("/users/{summonerName}")
    public ResponseEntity<UserInfoResponse> getUserInfo(@PathVariable String summonerName) {

        UserInfoResponse userInfoResponse = userInfoService.getSummonerInfo(summonerName);

        return ResponseEntity.ok(userInfoResponse);
    }

    @GetMapping("/users/{summonerName}/solorank")
    public ResponseEntity<LeagueEntryResponse> getSoloRank(@PathVariable String summonerName) {

        LeagueEntryResponse soloRankResponse = userRankService.getUserSoloRank(summonerName);
        return ResponseEntity.ok(soloRankResponse);
    }

    @GetMapping("/users/{summonerName}/flexrank")
    public ResponseEntity<LeagueEntryResponse> getFlexRank(@PathVariable String summonerName) {

        LeagueEntryResponse flexRankResponse = userRankService.getUserFlexRank(summonerName);
        return ResponseEntity.ok(flexRankResponse);
    }
}

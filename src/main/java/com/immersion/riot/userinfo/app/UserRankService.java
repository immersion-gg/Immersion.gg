package com.immersion.riot.userinfo.app;

import com.immersion.riot.userinfo.infra.client.UserInfoClient;
import com.immersion.riot.userinfo.infra.dto.LeagueEntryDTO;
import com.immersion.riot.userinfo.infra.dto.SummonerDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRankService {

    private final UserInfoClient userInfoClient;

    public List<LeagueEntryResponse> getSummonerRank(String encryptedSummonerId) {
        List<LeagueEntryDTO> summonerLeague = userInfoClient.getSummonerLeague(encryptedSummonerId);
        List<LeagueEntryResponse> summonerRank = new ArrayList<>();

        for (LeagueEntryDTO league : summonerLeague) {
            LeagueEntryResponse leagueEntryResponse = new LeagueEntryResponse(league.queueType(), league.rank(), league.tier(), league.leaguePoints());
            
            summonerRank.add(leagueEntryResponse);
        }

        if(summonerRank.isEmpty()) {
            LeagueEntryResponse soloRank = new LeagueEntryResponse("RANKED_SOLO_5X5", "Unranked", "", 0);
            LeagueEntryResponse flexRank = new LeagueEntryResponse("RANKED_FLEX_SR", "Unranked", "", 0);
            summonerRank.add(soloRank);
            summonerRank.add(flexRank);
        }

        if(summonerRank.size() == 1) {
            if (summonerRank.get(0).getQueueType().equals("RANKED_SOLO_5x5")) {
                LeagueEntryResponse flexRank = new LeagueEntryResponse("RANKED_FLEX_SR", "Unranked", "", 0);
                summonerRank.add(flexRank);
            } else if (summonerRank.get(0).getQueueType().equals("RANKED_FLEX_SR")) {
                LeagueEntryResponse soloRank = new LeagueEntryResponse("RANKED_SOLO_5X5", "Unranked", "", 0);
                summonerRank.add(soloRank);
            }
        }

        return summonerRank;
    }

    public LeagueEntryResponse getUserSoloRank(String summonerName) {
        SummonerDTO summonerDTO = userInfoClient.getSummoner(summonerName);
        List<LeagueEntryDTO> summonerLeague = userInfoClient.getSummonerLeague(summonerDTO.id());
        LeagueEntryResponse soloRank = new LeagueEntryResponse();

        for (LeagueEntryDTO league : summonerLeague) {
            if(league.queueType().equals("RANKED_SOLO_5x5")) {
                soloRank.setQueueType(league.queueType());
                soloRank.setRank(league.rank());
                soloRank.setTier(league.tier());
                soloRank.setLeaguePoints(league.leaguePoints());
            }
        }

        if(soloRank.getQueueType().isEmpty()) {
            soloRank.setQueueType("RANKED_SOLO_5x5");
            soloRank.setRank(null);
            soloRank.setTier(null);
            soloRank.setLeaguePoints(0);
        }

        return soloRank;
    }

    public LeagueEntryResponse getUserFlexRank(String summonerName) {
        SummonerDTO summonerDTO = userInfoClient.getSummoner(summonerName);
        List<LeagueEntryDTO> summonerLeague = userInfoClient.getSummonerLeague(summonerDTO.id());
        LeagueEntryResponse flexRank = new LeagueEntryResponse();

        for (LeagueEntryDTO league : summonerLeague) {
            if(league.queueType().equals("RANKED_FLEX_SR")) {
                flexRank.setQueueType(league.queueType());
                flexRank.setRank(league.rank());
                flexRank.setTier(league.tier());
                flexRank.setLeaguePoints(league.leaguePoints());
            }
        }

        if(flexRank.getQueueType().isEmpty()) {
            flexRank.setQueueType("RANKED_FLEX_SR");
            flexRank.setRank(null);
            flexRank.setTier(null);
            flexRank.setLeaguePoints(0);
        }

        return flexRank;
    }
}

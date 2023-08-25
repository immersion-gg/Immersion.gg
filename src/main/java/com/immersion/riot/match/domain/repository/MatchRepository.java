package com.immersion.riot.match.domain.repository;

import com.immersion.riot.match.domain.entity.Match;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatchRepository extends JpaRepository<Match, String> {

    @Query("SELECT m FROM Match m JOIN m.participants p WHERE p.puuid = :puuid")
    Slice<Match> getMatchListByPuuid(String puuid, Pageable pageable);

    @Query("SELECT m FROM Match m JOIN m.participants p WHERE p.summonerName = LOWER(:summonerName)")
    Slice<Match> getMatchListBySummonerName(String summonerName, Pageable pageable);

    @Query("SELECT m from Match m WHERE m.matchId " +
            "IN (SELECT p.matchId FROM Participant p WHERE p.puuid = :puuid " +
            "and p.championName = LOWER(:championName) GROUP BY p.matchId, p.championName)")
    List<Match> getMatchIdByPuuidAndChampionId(String puuid, String championName);
}

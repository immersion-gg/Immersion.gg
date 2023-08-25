package com.immersion.riot.spectator.infra;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public record SpectatorQueryDTO(
        String gameId,
        int mapId,
        String gameMode,
        String gameType,
        int gameQueueConfigId,
        List<CurrentGameParticipant> participants,
        CurrentGameParticipant.Observers observers,
        String platformId,
        List<BannedChampion> bannedChampions,
        Timestamp gameStartTime,
        long gameLength
) {

    public LocalTime getGameLength() {
        LocalDateTime startTime = gameStartTime.toLocalDateTime();
        Duration between = Duration.between(startTime, LocalDateTime.now());

        return LocalTime.ofSecondOfDay(between.getSeconds());
    }

    public record CurrentGameParticipant(
            int teamId,
            int spell1Id,
            int spell2Id,
            int championId,
            int profileIconId,
            String summonerName,
            boolean bot,
            String summonerId,
            List<Integer> gameCustomizationObjects,
            Perks perks
    ) {

        record Observers(String encryptionKey) {

        }
    }

    public record Perks(List<Integer> perkIds, String perkStyle, String perkSubStyle) {
    }

    record BannedChampion(int championId, int teamId, int pickTurn) {
    }
}

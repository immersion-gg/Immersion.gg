package com.immersion.riot.spectator.app;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.immersion.riot.spectator.domain.enums.QueueType;
import com.immersion.riot.spectator.domain.enums.TeamCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class SpectatorQueryResponse {
    private int mapId;
    private QueueType queueType;
    private List<Team> teams;
    @JsonFormat(pattern = "mm:ss")
    private LocalTime gameLength;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Team {
        private TeamCode teamCode;
        private List<CurrentGameParticipant> participants;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CurrentGameParticipant {
        private String summonerName;
        private String summonerId;
        private String spell1ImageUrl;
        private String spell2ImageUrl;
        private String championImageUrl;
    }
}

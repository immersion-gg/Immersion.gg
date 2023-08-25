package com.immersion.riot.spectator.app;

import com.immersion.riot.common.app.NoDataException;
import com.immersion.riot.match.app.service.ImageUrlBuilderService;
import com.immersion.riot.spectator.domain.enums.QueueType;
import com.immersion.riot.spectator.domain.enums.TeamCode;
import com.immersion.riot.spectator.infra.SpectatorClient;
import com.immersion.riot.spectator.infra.SpectatorQueryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.immersion.riot.spectator.infra.SpectatorQueryDTO.CurrentGameParticipant;

@Service
@RequiredArgsConstructor
public class SpectatorQueryService {
    private final SpectatorClient spectatorClient;
    private final ImageUrlBuilderService imageUrlBuilderService;

    public SpectatorQueryResponse getSpectator(String summonerId) {
        return spectatorClient.getSpectator(summonerId)
                .map(this::convert)
                .orElseThrow(NoDataException::new);
    }

    private SpectatorQueryResponse convert(SpectatorQueryDTO response) {
        Map<TeamCode, List<CurrentGameParticipant>> groupingByTeamCode = response.participants().stream()
                .collect(Collectors.groupingBy(it -> TeamCode.ofCode(it.teamId())));

        return SpectatorQueryResponse.builder()
                .mapId(response.mapId())
                .queueType(QueueType.ofQueueId(response.gameQueueConfigId()))
                .gameLength(response.getGameLength())
                .teams(groupingByTeamCode.entrySet().stream().map(it -> convert(it.getKey(), it.getValue())).toList())
                .build();
    }

    private SpectatorQueryResponse.Team convert(TeamCode teamCode, List<CurrentGameParticipant> participants) {
        return SpectatorQueryResponse.Team.builder()
                .teamCode(teamCode)
                .participants(participants.stream()
                        .map(participant -> SpectatorQueryResponse.CurrentGameParticipant.builder()
                                .spell1ImageUrl(imageUrlBuilderService.getSpellImageUrl(participant.spell1Id()))
                                .spell2ImageUrl(imageUrlBuilderService.getSpellImageUrl(participant.spell2Id()))
                                .championImageUrl(imageUrlBuilderService.getChampionImageUrl(participant.championId()))
                                .summonerName(participant.summonerName())
                                .summonerId(participant.summonerId())
                                .build())
                        .toList())
                .build();
    }
}

package com.immersion.riot.spectator.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum QueueType {
    CUSTOM_GAMES(0,"사용자설정"),
    RANKED_SOLO(430, "솔로랭크"),
    BLIND(430, "비공개선택"),
    RANKED_FLEX(440, "자유랭크"),
    HOWLING_ABYSS(450, "칼바람나락");

    private Integer id;
    private String name;

    public static QueueType ofQueueId(Integer queueId) {
        return Arrays.stream(values())
                .filter(queueType -> queueType.id.equals(queueId))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

}

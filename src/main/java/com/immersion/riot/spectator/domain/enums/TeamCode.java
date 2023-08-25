package com.immersion.riot.spectator.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum TeamCode {
    BLUE(100), RED(200);

    private Integer id;

    public static TeamCode ofCode(Integer id) {
        return Arrays.stream(TeamCode.values())
                .filter(teamCode -> teamCode.getId().equals(id))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}

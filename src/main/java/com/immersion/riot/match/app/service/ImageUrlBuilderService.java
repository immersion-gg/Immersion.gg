package com.immersion.riot.match.app.service;

import com.immersion.riot.common.app.NoDataException;
import com.immersion.riot.match.domain.repository.RedisRepository;
import com.immersion.riot.match.infra.client.CdnClient;
import com.immersion.riot.match.infra.dto.*;
import io.micrometer.common.util.StringUtils;
import io.netty.util.internal.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ImageUrlBuilderService {

    private final CdnClient cdnClient;
    private final RedisRepository redisRepository;

    @Value("${riot.versions}")
    private String VERSION;

    @Value("${riot.api.url.cdn}")
    private String CDN_URL;

    public String getSpellImageUrl(int spellKey) {

        String spellImage = redisRepository.getSpellImage(String.valueOf(spellKey));

        if (StringUtils.isBlank(spellImage)) {
            SummonerCastQueryResponse summonerCastResponse = cdnClient.getSummonerCast(VERSION);
            summonerCastResponse.data().values().forEach(summonerCastDto ->
                    redisRepository.setSpellImage(String.valueOf(summonerCastDto.key()), summonerCastDto.image().full()));


            return CDN_URL + VERSION + "/img/spell/"  + redisRepository.getSpellImage(String.valueOf(spellKey));
        }

        return CDN_URL + VERSION + "/img/spell/"  + spellImage;

    }

    public String getPerkImageUrl(int perkId) {

        String perkImage = redisRepository.getPerkImage(String.valueOf(perkId));

        if (StringUtils.isBlank(perkImage)) {
            List<SummonerPerksQueryResponse> summonerPerksQueryResponses = cdnClient.getSummonerPerk(VERSION);

            summonerPerksQueryResponses.forEach(summonerPerkResponse ->
                    redisRepository.setPerkImage(String.valueOf(summonerPerkResponse.id()),summonerPerkResponse.icon()));

            return CDN_URL + "img/" + redisRepository.getPerkImage(String.valueOf(perkId));
        }

        return CDN_URL + "img/" + perkImage;
    }

    public String getChampionImageUrl(int championKey) {

        String championImage = redisRepository.getChampionImage(String.valueOf(championKey));

        if (StringUtils.isBlank(championImage)) {
            ChampionQueryResponse championQueryResponse = cdnClient.getChampion(VERSION);

            championQueryResponse.data().values().forEach(
                    championDto -> redisRepository.setChampionImage(String.valueOf(championDto.key()), championDto.image().full())
            );
            return CDN_URL + VERSION + "/img/champion/" + redisRepository.getChampionImage(String.valueOf(championKey));
        }
        return CDN_URL + VERSION + "/img/champion/" + championImage;
    }

    public String getChampionImageUrlByName(String championName) {

        return CDN_URL + VERSION + "/img/champion/" + championName + ".png";
    }

    public String getItemImageUrl(int itemKey) {
        return CDN_URL + VERSION + "/img/item/" + itemKey + ".png";
    }
    
    public String getProfileImageUrl(int profileIconId) {
        return CDN_URL + VERSION + "/img/profileicon/" + profileIconId + ".png";
    }
}

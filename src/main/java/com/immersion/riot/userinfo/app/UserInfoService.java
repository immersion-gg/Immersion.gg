package com.immersion.riot.userinfo.app;

import com.immersion.riot.userinfo.infra.dto.SummonerDTO;
import com.immersion.riot.userinfo.infra.client.UserInfoClient;
import com.immersion.riot.match.app.service.ImageUrlBuilderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserInfoService {

    private final UserInfoClient userInfoClient;
    private final UserRankService userRankService;
    private final ImageUrlBuilderService iconImageUrlBuilderService;

    public UserInfoResponse getSummonerInfo(String summonerName) {
        SummonerDTO summonerDTO = userInfoClient.getSummoner(summonerName);
        UserInfoResponse userInfo = new UserInfoResponse();

        userInfo.setProfileImageUrl(iconImageUrlBuilderService.getProfileImageUrl(summonerDTO.profileIconId()));
        userInfo.setId(summonerDTO.id());
        userInfo.setName(summonerDTO.name());
        userInfo.setPuuid(summonerDTO.puuid());
        userInfo.setSummonerLevel(summonerDTO.summonerLevel());
        userInfo.setRank(userRankService.getSummonerRank(summonerDTO.id()));

        return userInfo;
    }


}

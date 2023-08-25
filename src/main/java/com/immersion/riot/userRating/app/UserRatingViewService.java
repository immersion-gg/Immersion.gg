package com.immersion.riot.userRating.app;

import com.immersion.riot.userRating.domain.UserRating;
import com.immersion.riot.userRating.domain.UserRatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRatingViewService {

    private final UserRatingRepository userRatingRepository;

    public double getAverageSkillRating(String puuid) {
        List<UserRating> ratings = userRatingRepository.findByPuuid(puuid);

        if (ratings.isEmpty()) {
            return 0.0;
        }

        double totalSkillRating = 0;

        for (UserRating rating : ratings) {
            totalSkillRating += rating.getSkillRating();
        }

        return totalSkillRating / ratings.size();
    }

    public double getAverageMannerRating(String puuid) {
        List<UserRating> ratings = userRatingRepository.findByPuuid(puuid);

        if (ratings.isEmpty()) {
            return 0.0;
        }

        double totalMannerRating = 0;

        for (UserRating rating : ratings) {
            totalMannerRating += rating.getMannerRating();
        }

        return totalMannerRating / ratings.size();
    }

    public double getAverageHonorRating(String puuid) {
        List<UserRating> ratings = userRatingRepository.findByPuuid(puuid);

        if (ratings.isEmpty()) {
            return 0.0;
        }

        double totalHonorRating = 0;

        for (UserRating rating : ratings) {
            totalHonorRating += rating.getHonorRating();
        }

        return totalHonorRating / ratings.size();
    }

    public List<Double> getUserRating(String puuid) {
        List<Double> userRating = new ArrayList<>();

        userRating.add(getAverageSkillRating(puuid));
        userRating.add(getAverageMannerRating(puuid));
        userRating.add(getAverageHonorRating(puuid));

        return userRating;
    }

    public double getInGameRatings(String puuid) {
        return (getAverageSkillRating(puuid)+getAverageMannerRating(puuid)+getAverageHonorRating(puuid))/3;
    }
}

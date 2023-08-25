package com.immersion.riot.userRating.app;

import com.immersion.riot.userRating.domain.UserRating;
import com.immersion.riot.userRating.domain.UserRatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserRatingService {

    private final UserRatingRepository userRatingRepository;

    public void rateUser(UserRatingRequest ratingRequest) {
        UserRating userRating = ratingRequest.toEntity();
        userRatingRepository.save(userRating);
    }
}

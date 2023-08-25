package com.immersion.riot.userRating.api;

import com.immersion.riot.userRating.app.UserRatingService;
import com.immersion.riot.userRating.app.UserRatingViewService;
import com.immersion.riot.userRating.app.UserRatingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserRatingApi {

    private final UserRatingService userRatingService;
    private final UserRatingViewService userRatingViewService;

    @PostMapping("/rating")
    public ResponseEntity<String> rateUser(@RequestBody UserRatingRequest ratingRequest) {
        userRatingService.rateUser(ratingRequest);
        return ResponseEntity.ok("Rated Successfully.");
    }

    @GetMapping("/rating/{puuid}")
    public ResponseEntity<List<Double>> getAverageRating(@PathVariable String puuid) {

        List<Double> userRatings = userRatingViewService.getUserRating(puuid);
        return ResponseEntity.ok(userRatings);
    }

    @GetMapping("/ingame/rating/{puuid}")
    public ResponseEntity<Double> getInGameRating(@PathVariable String puuid) {
        double InGameRating = userRatingViewService.getInGameRatings(puuid);
        return ResponseEntity.ok(InGameRating);
    }
}

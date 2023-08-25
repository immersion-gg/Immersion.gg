package com.immersion.riot.match.domain.repository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RedisRepository {

    private final RedisTemplate<String, String> redisTemplate;

    private HashOperations<String, String, String> hashOperations;

    private final String CHAMPION_KEY ="champion";
    private final String PERK_KEY = "perk";
    private final String SPELL_KEY = "spell";


    @PostConstruct
    public void init() {
        hashOperations = redisTemplate.opsForHash();
    }

    public void setChampionImage(String championId, String imagePath) {
        hashOperations.put(CHAMPION_KEY, championId, imagePath);
    }

    public String getChampionImage(String championId) {
        return hashOperations.get(CHAMPION_KEY, championId);
    }

    public void setPerkImage(String perkId, String imagePath) {
        hashOperations.put(PERK_KEY, perkId, imagePath);
    }

    public String getPerkImage(String perkId) {
        return hashOperations.get(PERK_KEY, perkId);
    }

    public void setSpellImage(String spellId, String imagePath) {
        hashOperations.put(SPELL_KEY, spellId, imagePath);
    }

    public String getSpellImage(String spellId) {
        return hashOperations.get(SPELL_KEY, spellId);
    }
}

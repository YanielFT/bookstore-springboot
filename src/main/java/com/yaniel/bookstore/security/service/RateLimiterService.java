package com.yaniel.bookstore.security.service;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimiterService {
    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();

    public Bucket resolveBucket(String key, int limit, int durationSeconds) {

        return cache.computeIfAbsent(key, k -> {
            Refill refill = Refill.greedy(limit, Duration.ofSeconds(durationSeconds));
            Bandwidth limitBandwidth = Bandwidth.classic(limit, refill);
            return Bucket.builder()
                    .addLimit(limitBandwidth)
                    .build();
        });
    }
}

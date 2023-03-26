package com.aua.movie.controller;

import com.aua.movie.dto.WatchableDto;
import com.aua.movie.service.WatchableService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recommend")
public class RecommendationController {

    private final WatchableService watchableService;

    @Value("${python.recommendation.endpoint.url}")
    private String recommendationUrl;

    @GetMapping
    public ResponseEntity<WatchableDto> recommendWatchable() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Integer> response = restTemplate.getForObject(recommendationUrl, Map.class);
        WatchableDto body = watchableService.getWatchable((long)response.get("id"));
        return ResponseEntity.ok(body);
    }
}

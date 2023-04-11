package com.aua.movie.controller;

import com.aua.movie.dto.WatchableDto;
import com.aua.movie.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recommend")
public class RecommendationController {

    private final RecommendationService recommendationService;

    @Value("${python.recommendation.endpoint.url}")
    private String recommendationUrl;

    @GetMapping
    public ResponseEntity<Page<WatchableDto>> recommendWatchables(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                        @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                                                        @RequestParam(value = "number", required = false, defaultValue = "1") Integer number) {
        RestTemplate restTemplate = new RestTemplate();
        Integer[] ids = restTemplate.getForObject(recommendationUrl + "?number=" + number, Integer[].class);
        Page<WatchableDto> body = null;
        if (ids != null) {
            body = recommendationService.findAllRecommended(Arrays.asList(ids), PageRequest.of(pageNumber,pageSize));
        }
        return ResponseEntity.ok(body);
    }
}

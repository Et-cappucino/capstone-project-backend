package com.aua.movie.service.impl;


import com.aua.movie.config.PageableHelper;
import com.aua.movie.dto.WatchableDto;
import com.aua.movie.mapper.WatchableMapper;
import com.aua.movie.model.Watchable;
import com.aua.movie.model.enums.Genre;
import com.aua.movie.model.enums.RecommendationType;
import com.aua.movie.repository.WatchableRepository;
import com.aua.movie.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

    private final WatchableRepository watchableRepository;
    private final WatchableMapper watchableMapper;
    private final PageableHelper pageableHelper;

    @Value("${python.recommendation.endpoint.url}")
    private String url;

    @Override
    public Page<WatchableDto> findAllRecommended(RecommendationType type, Long watchableId, Pageable pageRequest) {
        List<Long> ids = getRecommendedWatchableIds(type, watchableId);
        List<WatchableDto> watchableDtoList = watchableRepository.findAllById(ids)
                .stream()
                .map(watchableMapper::watchableToWatchableDto)
                .collect(Collectors.toList());
        return pageableHelper.listToPage(watchableDtoList, pageRequest.getPageNumber(), pageRequest.getPageSize());
    }

    private List<Long> getRecommendedWatchableIds(RecommendationType type, Long watchableId) {
        Watchable watchable = watchableRepository.findById(watchableId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        RestTemplate restTemplate = new RestTemplate();
        Integer[] ids = restTemplate.postForObject(url, payload(watchable.getGenres()), Integer[].class);
        List<Integer> idsAsList = Arrays.asList(Objects.requireNonNull(ids));
        return idsAsList.stream()
                .mapToLong(Integer::longValue)
                .boxed()
                .collect(Collectors.toList());
    }

    private HttpEntity<Map<String, Object>> payload(List<Genre> genres) {
        Map<String, Object> requestBody = Map.of("genres", genres);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(requestBody, headers);
    }
}

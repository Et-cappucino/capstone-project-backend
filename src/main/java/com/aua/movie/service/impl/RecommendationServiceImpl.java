package com.aua.movie.service.impl;


import com.aua.movie.dto.WatchableDto;
import com.aua.movie.mapper.WatchableMapper;
import com.aua.movie.model.Profile;
import com.aua.movie.model.enums.Genre;
import com.aua.movie.repository.ProfileRepository;
import com.aua.movie.repository.WatchableRepository;
import com.aua.movie.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

    private final ProfileRepository profileRepository;
    private final WatchableRepository watchableRepository;
    private final WatchableMapper watchableMapper;

    @Value("${python.recommendation.endpoint.url}")
    private String url;

    @Override
    public Page<WatchableDto> findAllRecommended(Integer number, Long profileId, Pageable pageRequest) {
        List<Long> ids = getRecommendedWatchableIds(number, profileId);
        List<WatchableDto> watchableDtoList = watchableRepository.findAllById(ids)
                .stream()
                .map(watchableMapper::watchableToWatchableDto)
                .collect(Collectors.toList());
        return listToPage(watchableDtoList, pageRequest.getPageNumber(), pageRequest.getPageSize());
    }

    private List<Long> getRecommendedWatchableIds(Integer number, Long profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        RestTemplate restTemplate = new RestTemplate();
        Integer[] ids = restTemplate.postForObject(url, payload(number, profile.getFavoriteGenres()), Integer[].class);
        List<Integer> idsAsList = Arrays.asList(Objects.requireNonNull(ids));
        return idsAsList.stream()
                .mapToLong(Integer::longValue)
                .boxed()
                .collect(Collectors.toList());
    }

    private HttpEntity<Map<String, Object>> payload(Integer number, Set<Genre> genres) {
        Map<String, Object> requestBody = Map.of("number", number, "genres", genres);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(requestBody, headers);
    }

    private Page<WatchableDto> listToPage(List<WatchableDto> recommendations, int pageNumber, int pageSize) {
        int totalElements = recommendations.size();
        int totalPages = (int) Math.ceil((double) totalElements / pageSize);

        if (pageNumber >= totalPages) {
            return new PageImpl<>(Collections.emptyList(), PageRequest.of(pageNumber, pageSize), totalElements);
        }

        int fromIndex = pageNumber * pageSize;
        int toIndex = Math.min(fromIndex + pageSize, totalElements);
        return new PageImpl<>(recommendations.subList(fromIndex, toIndex), PageRequest.of(pageNumber, pageSize), totalElements);
    }
}

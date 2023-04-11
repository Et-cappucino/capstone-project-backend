package com.aua.movie.service.impl;


import com.aua.movie.dto.WatchableDto;
import com.aua.movie.mapper.WatchableMapper;
import com.aua.movie.repository.WatchableRepository;
import com.aua.movie.service.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {

    private final WatchableRepository watchableRepository;
    private final WatchableMapper watchableMapper;

    @Override
    public Page<WatchableDto> findAllRecommended(List<Integer> ids, Pageable pageRequest) {
        List<Long> longIds = ids.stream()
                .mapToLong(Integer::longValue)
                .boxed()
                .collect(Collectors.toList());
        List<WatchableDto> watchableDtoList = watchableRepository.findAllById(longIds)
                .stream()
                .map(watchableMapper::watchableToWatchableDto)
                .collect(Collectors.toList());
        return listToPage(watchableDtoList, pageRequest.getPageNumber(), pageRequest.getPageSize());
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

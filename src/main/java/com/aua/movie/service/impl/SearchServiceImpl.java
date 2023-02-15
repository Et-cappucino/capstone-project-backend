package com.aua.movie.service.impl;

import com.aua.movie.dto.WatchableDto;
import com.aua.movie.mapper.WatchableMapper;
import com.aua.movie.repository.WatchableRepository;
import com.aua.movie.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final WatchableRepository watchableRepository;
    private final WatchableMapper watchableMapper;

    @Override
    public Page<WatchableDto> searchWatchable(String query, Pageable pageRequest) {
        if (query != null && !query.isEmpty()) {
            return watchableRepository.findByNameStartingWithIgnoreCase(query, pageRequest)
                    .map(watchableMapper::watchableToWatchableDto);
        } else {
            return null;
        }
    }
}
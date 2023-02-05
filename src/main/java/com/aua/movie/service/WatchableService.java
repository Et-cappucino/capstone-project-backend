package com.aua.movie.service;

import com.aua.movie.dto.WatchableDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WatchableService {

    Page<WatchableDto> findAll(Pageable pageRequest);

    Page<WatchableDto> findAllMovies(Pageable pageRequest);

    Page<WatchableDto> findAllSeries(Pageable pageRequest);

    WatchableDto getWatchable(Long id);

    WatchableDto registerWatchable(WatchableDto watchableDto);

    WatchableDto updateWatchable(WatchableDto watchableDto, Long id);

    void deleteWatchable(Long id);

    Page<WatchableDto> findLatest(Pageable pageRequest);

    Page<WatchableDto> findPopular(Pageable pageRequest);

    Page<WatchableDto> findUpcoming(Pageable pageRequest);
}

package com.aua.movie.service;

import com.aua.movie.dto.WatchableDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FilteredWatchablesService {

    Page<WatchableDto> findAllMovies(Pageable pageRequest);

    Page<WatchableDto> findAllSeries(Pageable pageRequest);

    Page<WatchableDto> findLatest(Pageable pageRequest);

    Page<WatchableDto> findPopular(Pageable pageRequest);

    Page<WatchableDto> findUpcoming(Pageable pageRequest);

    Page<WatchableDto> findLatestMovies(Pageable pageRequest);

    Page<WatchableDto> findPopularMovies(Pageable pageRequest);

    Page<WatchableDto> findUpcomingMovies(Pageable pageRequest);

    Page<WatchableDto> findLatestSeries(Pageable pageRequest);

    Page<WatchableDto> findPopularSeries(Pageable pageRequest);

    Page<WatchableDto> findUpcomingSeries(Pageable pageRequest);
}

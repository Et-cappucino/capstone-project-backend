package com.aua.movie.service;

import com.aua.movie.dto.WatchableDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FilteredWatchablesService {

    Page<WatchableDto> findLatestMovies(Pageable pageRequest);

    Page<WatchableDto> findPopularMovies(Pageable pageRequest);

    Page<WatchableDto> findUpcomingMovies(Pageable pageRequest);

    Page<WatchableDto> findLatestSeries(Pageable pageRequest);

    Page<WatchableDto> findPopularSeries(Pageable pageRequest);

    Page<WatchableDto> findUpcomingSeries(Pageable pageRequest);
}

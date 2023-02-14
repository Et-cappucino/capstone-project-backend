package com.aua.movie.service.impl;

import com.aua.movie.dto.WatchableDto;
import com.aua.movie.mapper.WatchableMapper;
import com.aua.movie.repository.WatchableRepository;
import com.aua.movie.service.FilteredWatchablesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.aua.movie.model.enums.WatchableType.MOVIE;
import static com.aua.movie.model.enums.WatchableType.SERIES;

@Service
@RequiredArgsConstructor
public class FilteredWatchablesServiceImpl implements FilteredWatchablesService {

    private final WatchableRepository watchableRepository;
    private final WatchableMapper watchableMapper;

    @Value("${watchableService.popular.rating.min}")
    private double minRating;

    @Value("${watchableService.latestDate.months}")
    private long months;

    @Override
    public Page<WatchableDto> findLatestMovies(Pageable pageRequest) {
        LocalDate cutOffDate = LocalDate.now().minusMonths(months);
        return watchableRepository.findLatest(cutOffDate, LocalDate.now(), MOVIE, pageRequest)
                .map(watchableMapper::watchableToWatchableDto);
    }

    @Override
    public Page<WatchableDto> findPopularMovies(Pageable pageRequest) {
        return watchableRepository.findPopular(minRating, MOVIE, pageRequest)
                .map(watchableMapper::watchableToWatchableDto);
    }

    @Override
    public Page<WatchableDto> findUpcomingMovies(Pageable pageRequest) {
        return watchableRepository.findUpcoming(LocalDate.now(), MOVIE, pageRequest)
                .map(watchableMapper::watchableToWatchableDto);
    }

    @Override
    public Page<WatchableDto> findLatestSeries(Pageable pageRequest) {
        LocalDate cutOffDate = LocalDate.now().minusMonths(months);
        return watchableRepository.findLatest(cutOffDate, LocalDate.now(), SERIES, pageRequest)
                .map(watchableMapper::watchableToWatchableDto);
    }

    @Override
    public Page<WatchableDto> findPopularSeries(Pageable pageRequest) {
        return watchableRepository.findPopular(minRating, SERIES, pageRequest)
                .map(watchableMapper::watchableToWatchableDto);
    }

    @Override
    public Page<WatchableDto> findUpcomingSeries(Pageable pageRequest) {
        return watchableRepository.findUpcoming(LocalDate.now(), SERIES, pageRequest)
                .map(watchableMapper::watchableToWatchableDto);
    }
}

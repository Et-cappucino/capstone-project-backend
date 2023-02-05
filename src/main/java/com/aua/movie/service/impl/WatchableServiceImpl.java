package com.aua.movie.service.impl;

import com.aua.movie.dto.WatchableDto;
import com.aua.movie.mapper.WatchableMapper;
import com.aua.movie.model.Watchable;
import com.aua.movie.repository.WatchableRepository;
import com.aua.movie.service.WatchableService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

import static com.aua.movie.model.enums.WatchableType.MOVIE;
import static com.aua.movie.model.enums.WatchableType.SERIES;

@Service
@RequiredArgsConstructor
public class WatchableServiceImpl implements WatchableService {

    private final WatchableRepository watchableRepository;
    private final WatchableMapper watchableMapper;

    @Value("${watchableService.popular.rating.min}")
    private double minRating;

    @Value("${watchableService.latestDate.months}")
    private long months;

    @Override
    public Page<WatchableDto> findAll(Pageable pageRequest) {
        return watchableRepository.findAll(pageRequest)
                .map(watchableMapper::watchableToWatchableDto);
    }

    @Override
    public WatchableDto getWatchable(Long id) {
        Watchable watchable = watchableRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return watchableMapper.watchableToWatchableDto(watchable);
    }

    @Override
    public WatchableDto registerWatchable(WatchableDto watchableDto) {
        Watchable watchable = watchableMapper.watchableDtoToWatchable(watchableDto);
        watchableRepository.save(watchable);
        return watchableMapper.watchableToWatchableDto(watchable);
    }

    @Override
    public WatchableDto updateWatchable(WatchableDto watchableDto, Long id) {
        Watchable watchable = watchableRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Watchable updatedWatchable = update(watchable, watchableMapper.watchableDtoToWatchable(watchableDto));

        watchableRepository.save(updatedWatchable);
        return watchableMapper.watchableToWatchableDto(updatedWatchable);
    }

    @Override
    public void deleteWatchable(Long id) {
        watchableRepository.deleteById(id);
    }

    @Override
    public Page<WatchableDto> findLatest(Pageable pageRequest) {
        LocalDate cutOffDate = LocalDate.now().minusMonths(months);
        return watchableRepository.findAllLatest(cutOffDate, LocalDate.now(), pageRequest)
                .map(watchableMapper::watchableToWatchableDto);
    }

    @Override
    public Page<WatchableDto> findPopular(Pageable pageRequest) {
        return watchableRepository.findAllPopular(minRating, pageRequest)
                .map(watchableMapper::watchableToWatchableDto);
    }

    @Override
    public Page<WatchableDto> findUpcoming(Pageable pageRequest) {
        return watchableRepository.findAllUpcoming(LocalDate.now(), pageRequest)
                .map(watchableMapper::watchableToWatchableDto);
    }

    @Override
    public Page<WatchableDto> findAllMovies(Pageable pageRequest) {
        return watchableRepository.findAllByType(MOVIE, pageRequest)
                .map(watchableMapper::watchableToWatchableDto);
    }

    @Override
    public Page<WatchableDto> findAllSeries(Pageable pageRequest) {
        return watchableRepository.findAllByType(SERIES, pageRequest)
                .map(watchableMapper::watchableToWatchableDto);
    }

    private Watchable update(Watchable current, Watchable updated) {
        current.setName(updated.getName());
        current.setType(updated.getType());
        current.setDuration(updated.getDuration());
        current.setRating(updated.getRating());
        current.setTrailerLink(updated.getTrailerLink());
        current.setPosterPath(updated.getPosterPath());
        current.setMainBackdropPath(updated.getMainBackdropPath());
        current.setBackdropPaths(updated.getBackdropPaths());
        current.setReleaseDate(updated.getReleaseDate());
        current.setDescription(updated.getDescription());
        current.setGenres(updated.getGenres());
        current.setCast(updated.getCast());
        return current;
    }
}

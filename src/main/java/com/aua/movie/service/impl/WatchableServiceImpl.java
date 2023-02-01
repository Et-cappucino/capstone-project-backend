package com.aua.movie.service.impl;

import com.aua.movie.dto.WatchableDto;
import com.aua.movie.mapper.WatchableMapper;
import com.aua.movie.model.Watchable;
import com.aua.movie.repository.WatchableRepository;
import com.aua.movie.service.WatchableService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<WatchableDto> findAll() {
        List<Watchable> watchables = watchableRepository.findAll();
        return watchables.stream()
                .map(watchableMapper::watchableToWatchableDto)
                .collect(Collectors.toList());
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
    public List<WatchableDto> findLatest() {
        List<Watchable> watchables = watchableRepository.findAll();
        LocalDate latestDate = LocalDate.now().minusMonths(months);

        return watchables.stream()
                .filter(watchable -> watchable.getReleaseDate().isAfter(latestDate)
                        && watchable.getReleaseDate().isBefore(LocalDate.now()))
                .map(watchableMapper::watchableToWatchableDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<WatchableDto> findPopular() {
        List<Watchable> watchables = watchableRepository.findAll();

        return watchables.stream()
                .filter(watchable -> watchable.getRating() >= minRating)
                .map(watchableMapper::watchableToWatchableDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<WatchableDto> findUpcoming() {
        List<Watchable> watchables = watchableRepository.findAll();

        return watchables.stream()
                .filter(watchable -> watchable.getReleaseDate().isAfter(LocalDate.now()))
                .map(watchableMapper::watchableToWatchableDto)
                .collect(Collectors.toList());
    }

    private Watchable update(Watchable current, Watchable updated) {
        current.setName(updated.getName());
        current.setType(updated.getType());
        current.setDuration(updated.getDuration());
        current.setRating(updated.getRating());
        current.setTrailerLink(updated.getTrailerLink());
        current.setPosterPath(updated.getPosterPath());
        current.setBackdropPaths(updated.getBackdropPaths());
        current.setReleaseDate(updated.getReleaseDate());
        current.setDescription(updated.getDescription());
        current.setGenres(updated.getGenres());
        current.setCast(updated.getCast());
        return current;
    }
}

package com.aua.movie.service;

import com.aua.movie.dto.WatchableDto;

import java.util.List;

public interface WatchableService {

    List<WatchableDto> findAll();

    WatchableDto getWatchable(Long id);

    WatchableDto registerWatchable(WatchableDto watchableDto);

    WatchableDto updateWatchable(WatchableDto watchableDto, Long id);

    void deleteWatchable(Long id);

    List<WatchableDto> findLatest();

    List<WatchableDto> findPopular();

    List<WatchableDto> findUpcoming();
}

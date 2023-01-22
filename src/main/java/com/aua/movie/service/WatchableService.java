package com.aua.movie.service;

import com.aua.movie.dto.WatchableDto;

import java.util.List;

public interface WatchableService {

    List<WatchableDto> findAll();

    WatchableDto getWatchable(Long id);

    WatchableDto registerWatchable(WatchableDto watchableDto);

    void updateWatchable(WatchableDto watchableDto);

    void deleteWatchable(Long id);
}

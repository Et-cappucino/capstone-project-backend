package com.aua.movie.service;

import com.aua.movie.dto.WatchableDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WatchableService {

    Page<WatchableDto> findAll(Pageable pageRequest);

    WatchableDto getWatchable(Long id);

    WatchableDto registerWatchable(WatchableDto watchableDto);

    WatchableDto updateWatchable(WatchableDto watchableDto, Long id);

    void deleteWatchable(Long id);
}

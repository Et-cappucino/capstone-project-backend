package com.aua.movie.service;

import com.aua.movie.dto.WatchableDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WatchlistService {

    void addToWatchlist(Long watchableId, Long profileId);

    void removeFromWatchlist(Long watchableId, Long profileId);

    Page<WatchableDto> getProfileWatchlist(Long profileId, Pageable pageRequest);
}

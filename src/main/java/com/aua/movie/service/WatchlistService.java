package com.aua.movie.service;

import com.aua.movie.dto.WatchableDto;

import java.util.List;

public interface WatchlistService {

    void addToWatchlist(Long watchableId, Long profileId);

    void removeFromWatchlist(Long watchableId, Long profileId);

    List<WatchableDto> getProfileWatchlist(Long profileId);
}

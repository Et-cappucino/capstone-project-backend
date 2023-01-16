package com.aua.movie.service;

import com.aua.movie.model.Watchable;

import java.util.List;

public interface WatchlistService {

    void addToWatchlist(Long watchableId, Long profileId);

    void removeFromWatchlist(Long watchableId, Long profileId);

    List<Watchable> getProfileWatchlist(Long profileId);
}

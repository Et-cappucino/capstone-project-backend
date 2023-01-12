package com.aua.movie.service;

import com.aua.movie.model.Watchable;

import java.util.List;

public interface WatchableService {

    List<Watchable> findAll();

    Watchable getWatchable(Long id);

    Watchable registerWatchable(Watchable watchable);

    void updateWatchable(Watchable watchable);

    void deleteWatchable(Long id);
}

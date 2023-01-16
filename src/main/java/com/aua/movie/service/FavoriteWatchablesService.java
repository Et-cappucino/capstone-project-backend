package com.aua.movie.service;

import com.aua.movie.model.Watchable;

import java.util.List;

public interface FavoriteWatchablesService {

    void addToFavorites(Long watchableId, Long profileId);

    void removeFromFavorites(Long watchableId, Long profileId);

    List<Watchable> getProfileFavorites(Long profileId);
}

package com.aua.movie.service;

import com.aua.movie.dto.WatchableDto;

import java.util.List;

public interface FavoriteWatchablesService {

    void addToFavorites(Long watchableId, Long profileId);

    void removeFromFavorites(Long watchableId, Long profileId);

    List<WatchableDto> getProfileFavorites(Long profileId);
}

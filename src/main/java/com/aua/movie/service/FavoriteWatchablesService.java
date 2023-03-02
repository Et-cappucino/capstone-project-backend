package com.aua.movie.service;

import com.aua.movie.dto.WatchableDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FavoriteWatchablesService {

    void addToFavorites(Long watchableId, Long profileId);

    void removeFromFavorites(Long watchableId, Long profileId);

    Page<WatchableDto> getProfileFavorites(Long profileId, Pageable pageRequest);
}

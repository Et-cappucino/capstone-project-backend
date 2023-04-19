package com.aua.movie.service.impl;

import com.aua.movie.config.PageableHelper;
import com.aua.movie.dto.WatchableDto;
import com.aua.movie.mapper.WatchableMapper;
import com.aua.movie.model.Profile;
import com.aua.movie.model.Watchable;
import com.aua.movie.repository.ProfileRepository;
import com.aua.movie.repository.WatchableRepository;
import com.aua.movie.service.FavoriteWatchablesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FavoriteWatchablesServiceImpl implements FavoriteWatchablesService {

    private final ProfileRepository profileRepository;
    private final WatchableRepository watchableRepository;
    private final WatchableMapper watchableMapper;
    private final PageableHelper pageableHelper;

    @Override
    public void addToFavorites(Long watchableId, Long profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Watchable watchable = watchableRepository.findById(watchableId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        List<Watchable> favorites = profile.getFavorites();
        if (!favorites.contains(watchable)) {
            favorites.add(watchable);
            profileRepository.save(profile);
        }
    }

    @Override
    public void removeFromFavorites(Long watchableId, Long profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Watchable watchable = watchableRepository.findById(watchableId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        List<Watchable> favorites = profile.getFavorites();
        if (favorites.contains(watchable)) {
            favorites.remove(watchable);
            profileRepository.save(profile);
        }
    }

    @Override
    public Page<WatchableDto> getProfileFavorites(Long profileId, Pageable pageRequest) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<WatchableDto> favorites = profile.getFavorites()
                .stream()
                .map(watchableMapper::watchableToWatchableDto)
                .collect(Collectors.toList());
        return pageableHelper.listToPage(favorites, pageRequest.getPageNumber(), pageRequest.getPageSize());
    }
}

package com.aua.movie.service.impl;

import com.aua.movie.config.PageableHelper;
import com.aua.movie.dto.WatchableDto;
import com.aua.movie.mapper.WatchableMapper;
import com.aua.movie.model.Profile;
import com.aua.movie.model.Watchable;
import com.aua.movie.repository.ProfileRepository;
import com.aua.movie.repository.WatchableRepository;
import com.aua.movie.service.WatchlistService;
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
public class WatchlistServiceImpl implements WatchlistService {

    private final ProfileRepository profileRepository;
    private final WatchableRepository watchableRepository;
    private final WatchableMapper watchableMapper;
    private final PageableHelper pageableHelper;

    @Override
    public void addToWatchlist(Long watchableId, Long profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Watchable watchable = watchableRepository.findById(watchableId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        List<Watchable> watchlist = profile.getWatchlist();
        if (!watchlist.contains(watchable)) {
            watchlist.add(watchable);
            profileRepository.save(profile);
        }
    }

    @Override
    public void removeFromWatchlist(Long watchableId, Long profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Watchable watchable = watchableRepository.findById(watchableId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        List<Watchable> watchlist = profile.getWatchlist();
        if (watchlist.contains(watchable)) {
            watchlist.remove(watchable);
            profileRepository.save(profile);
        }
    }

    @Override
    public Page<WatchableDto> getProfileWatchlist(Long profileId, Pageable pageRequest) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<WatchableDto> watchlist = profile.getWatchlist()
                .stream()
                .map(watchableMapper::watchableToWatchableDto)
                .collect(Collectors.toList());
        return pageableHelper.listToPage(watchlist, pageRequest.getPageNumber(), pageRequest.getPageSize());
    }
}

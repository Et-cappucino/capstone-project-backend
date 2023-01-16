package com.aua.movie.service.impl;

import com.aua.movie.model.Profile;
import com.aua.movie.model.Watchable;
import com.aua.movie.repository.ProfileRepository;
import com.aua.movie.repository.WatchableRepository;
import com.aua.movie.service.WatchlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WatchlistServiceImpl implements WatchlistService {

    private final ProfileRepository profileRepository;
    private final WatchableRepository watchableRepository;

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
    public List<Watchable> getProfileWatchlist(Long profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return profile.getWatchlist();
    }
}

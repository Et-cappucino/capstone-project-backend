package com.aua.movie.service.impl;

import com.aua.movie.model.Watchable;
import com.aua.movie.repository.WatchableRepository;
import com.aua.movie.service.WatchableService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WatchableServiceImpl implements WatchableService {

    private final WatchableRepository watchableRepository;

    @Override
    public List<Watchable> findAll() {
        return watchableRepository.findAll();
    }

    @Override
    public Watchable getWatchable(Long id) {
        return watchableRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public Watchable registerWatchable(Watchable watchable) {
        return watchableRepository.save(watchable);
    }

    @Override
    public void updateWatchable(Watchable watchable) {
        watchableRepository.save(watchable);
    }

    @Override
    public void deleteWatchable(Long id) {
        watchableRepository.deleteById(id);
    }
}

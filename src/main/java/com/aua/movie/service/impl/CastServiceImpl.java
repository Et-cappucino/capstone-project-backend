package com.aua.movie.service.impl;

import com.aua.movie.model.Actor;
import com.aua.movie.model.Watchable;
import com.aua.movie.repository.ActorRepository;
import com.aua.movie.repository.WatchableRepository;
import com.aua.movie.service.CastService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CastServiceImpl implements CastService {

    private final WatchableRepository watchableRepository;
    private final ActorRepository actorRepository;

    @Override
    public void addToCast(Long actorId, Long watchableId) {
        Watchable watchable = watchableRepository.findById(watchableId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Actor actor = actorRepository.findById(actorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<Actor> cast = watchable.getCast();
        if (!cast.contains(actor)) {
            cast.add(actor);
            watchableRepository.save(watchable);
        }
    }

    @Override
    public void removeFromCast(Long actorId, Long watchableId) {
        Watchable watchable = watchableRepository.findById(watchableId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Actor actor = actorRepository.findById(actorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<Actor> cast = watchable.getCast();
        if (cast.contains(actor)) {
            cast.remove(actor);
            watchableRepository.save(watchable);
        }
    }

    @Override
    public List<Actor> getWatchableCast(Long watchableId) {
        Watchable watchable = watchableRepository.findById(watchableId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return watchable.getCast();
    }
}

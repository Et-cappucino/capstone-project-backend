package com.aua.movie.service.impl;

import com.aua.movie.model.Actor;
import com.aua.movie.repository.ActorRepository;
import com.aua.movie.service.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;

    @Override
    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

    @Override
    public Actor getActor(Long id) {
        return actorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public Actor registerActor(Actor actor) {
        return actorRepository.save(actor);
    }

    @Override
    public void updateActor(Actor actor) {
        actorRepository.save(actor);
    }

    @Override
    public void deleteActor(Long id) {
        actorRepository.deleteById(id);
    }
}

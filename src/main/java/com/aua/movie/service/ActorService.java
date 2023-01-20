package com.aua.movie.service;

import com.aua.movie.model.Actor;

import java.util.List;

public interface ActorService {

    List<Actor> findAll();

    Actor getActor(Long id);

    Actor registerActor(Actor actor);

    void updateActor(Actor actor);

    void deleteActor(Long id);
}

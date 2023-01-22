package com.aua.movie.service;

import com.aua.movie.dto.ActorDto;

import java.util.List;

public interface ActorService {

    List<ActorDto> findAll();

    ActorDto getActor(Long id);

    ActorDto registerActor(ActorDto actorDto);

    void updateActor(ActorDto actorDto);

    void deleteActor(Long id);
}

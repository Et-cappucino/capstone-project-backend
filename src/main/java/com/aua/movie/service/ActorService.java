package com.aua.movie.service;

import com.aua.movie.dto.ActorDto;

import java.util.List;

public interface ActorService {

    List<ActorDto> findAll();

    ActorDto getActor(Long id);

    ActorDto registerActor(ActorDto actorDto);

    ActorDto updateActor(ActorDto actorDto, Long id);

    void deleteActor(Long id);
}

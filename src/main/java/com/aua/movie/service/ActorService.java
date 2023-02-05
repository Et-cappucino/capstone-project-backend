package com.aua.movie.service;

import com.aua.movie.dto.ActorDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ActorService {

    Page<ActorDto> findAll(Pageable pageRequest);

    ActorDto getActor(Long id);

    ActorDto registerActor(ActorDto actorDto);

    ActorDto updateActor(ActorDto actorDto, Long id);

    void deleteActor(Long id);
}

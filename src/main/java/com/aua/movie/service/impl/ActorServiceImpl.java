package com.aua.movie.service.impl;

import com.aua.movie.dto.ActorDto;
import com.aua.movie.mapper.ActorMapper;
import com.aua.movie.model.Actor;
import com.aua.movie.repository.ActorRepository;
import com.aua.movie.service.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;
    private final ActorMapper actorMapper;

    @Override
    public Page<ActorDto> findAll(Pageable pageRequest) {
        return actorRepository.findAll(pageRequest)
                .map(actorMapper::actorToActorDto);
    }

    @Override
    public ActorDto getActor(Long id) {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return actorMapper.actorToActorDto(actor);
    }

    @Override
    public ActorDto registerActor(ActorDto actorDto) {
        Actor actor = actorMapper.actorDtoToActor(actorDto);
        actorRepository.save(actor);
        return actorMapper.actorToActorDto(actor);
    }

    @Override
    public ActorDto updateActor(ActorDto actorDto, Long id) {
        Actor actor = actorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Actor updatedActor = update(actor, actorMapper.actorDtoToActor(actorDto));

        actorRepository.save(updatedActor);
        return actorMapper.actorToActorDto(updatedActor);
    }

    @Override
    public void deleteActor(Long id) {
        actorRepository.deleteById(id);
    }

    private Actor update(Actor current, Actor updated) {
        current.setFirstName(updated.getFirstName());
        current.setLastName(updated.getLastName());
        current.setBirthDate(updated.getBirthDate());
        current.setBio(updated.getBio());
        current.setImagePath(updated.getImagePath());
        return current;
    }
}

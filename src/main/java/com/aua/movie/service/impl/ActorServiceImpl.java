package com.aua.movie.service.impl;

import com.aua.movie.dto.ActorDto;
import com.aua.movie.mapper.ActorMapper;
import com.aua.movie.model.Actor;
import com.aua.movie.repository.ActorRepository;
import com.aua.movie.service.ActorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActorServiceImpl implements ActorService {

    private final ActorRepository actorRepository;
    private final ActorMapper actorMapper;

    @Override
    public List<ActorDto> findAll() {
        List<Actor> actors = actorRepository.findAll();
        return actors.stream()
                .map(actorMapper::actorToActorDto)
                .collect(Collectors.toList());
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
        Actor updatedActor = actorMapper.actorDtoToActor(actorDto);

        actor.setFirstName(updatedActor.getFirstName());
        actor.setLastName(updatedActor.getLastName());
        actor.setBirthDate(updatedActor.getBirthDate());
        actor.setBio(updatedActor.getBio());

        actorRepository.save(actor);
        return actorMapper.actorToActorDto(actor);
    }

    @Override
    public void deleteActor(Long id) {
        actorRepository.deleteById(id);
    }
}

package com.aua.movie.mapper;

import com.aua.movie.dto.ActorDto;
import com.aua.movie.model.Actor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ActorMapper {

    ActorDto actorToActorDto(Actor actor);

    @Mapping(target = "id", ignore = true)
    Actor actorDtoToActor(ActorDto actorDto);
}

package com.aua.movie.service;

import com.aua.movie.dto.ActorDto;

import java.util.List;

public interface CastService {

    void addToCast(Long actorId, Long watchableId);

    void removeFromCast(Long actorId, Long watchableId);

    List<ActorDto> getWatchableCast(Long watchableId);
}

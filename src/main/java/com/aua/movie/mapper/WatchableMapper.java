package com.aua.movie.mapper;

import com.aua.movie.dto.WatchableDto;
import com.aua.movie.model.Watchable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WatchableMapper {

    WatchableDto watchableToWatchableDto(Watchable watchable);

    @Mapping(target = "id", ignore = true)
    Watchable watchableDtoToWatchable(WatchableDto watchableDto);
}

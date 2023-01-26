package com.aua.movie.mapper;

import com.aua.movie.dto.WatchableDto;
import com.aua.movie.model.Watchable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface WatchableMapper {

    @Mapping(target = "comments", source = "watchable.comments")
    WatchableDto watchableToWatchableDto(Watchable watchable);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "comments", source = "watchableDto.comments")
    Watchable watchableDtoToWatchable(WatchableDto watchableDto);
}

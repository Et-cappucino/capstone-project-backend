package com.aua.movie.mapper;

import com.aua.movie.dto.ProfileDto;
import com.aua.movie.model.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    @Mapping(target = "password", ignore = true)
    ProfileDto profileToProfileDto(Profile profile);

    @Mapping(target = "id", ignore = true)
    Profile profileDtoToProfile(ProfileDto profileDto);
}
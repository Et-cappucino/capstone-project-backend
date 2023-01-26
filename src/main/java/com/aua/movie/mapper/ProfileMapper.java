package com.aua.movie.mapper;

import com.aua.movie.dto.ProfileDto;
import com.aua.movie.model.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfileMapper {

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "comments", source = "profile.comments")
    ProfileDto profileToProfileDto(Profile profile);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "comments", source = "profileDto.comments")
    Profile profileDtoToProfile(ProfileDto profileDto);
}
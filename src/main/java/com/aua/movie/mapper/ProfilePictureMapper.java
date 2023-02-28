package com.aua.movie.mapper;

import com.aua.movie.dto.ProfilePictureDto;
import com.aua.movie.model.ProfilePicture;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfilePictureMapper {

    ProfilePictureDto profilePictureToProfilePictureDto(ProfilePicture profilePicture);

    @Mapping(target = "id", ignore = true)
    ProfilePicture profilePictureDtoToProfilePicture(ProfilePictureDto profilePictureDto);
}

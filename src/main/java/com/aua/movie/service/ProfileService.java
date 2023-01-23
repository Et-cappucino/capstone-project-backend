package com.aua.movie.service;

import com.aua.movie.dto.ProfileDto;

import java.util.List;

public interface ProfileService {

    List<ProfileDto> findAll();

    ProfileDto getProfile(Long id);

    ProfileDto registerProfile(ProfileDto profile);

    ProfileDto updateProfile(ProfileDto profile, Long id);

    void deleteProfile(Long id);
}

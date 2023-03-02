package com.aua.movie.service;

import com.aua.movie.dto.ProfileDto;
import com.aua.movie.model.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProfileService {

    Page<ProfileDto> findAll(Pageable pageRequest);

    ProfileDto getProfile(Long id);

    ProfileDto registerProfile(ProfileDto profile);

    ProfileDto updateProfile(ProfileDto profile, Long id);

    void deleteProfile(Long id);

    Profile getProfileByEmail(String email);

    ProfileDto findProfileByEmail(String email);

    boolean isProfileEnabled(Long profileId);
}

package com.aua.movie.service.impl;

import com.aua.movie.dto.ProfileDto;
import com.aua.movie.mapper.ProfileMapper;
import com.aua.movie.model.Profile;
import com.aua.movie.repository.ProfileRepository;
import com.aua.movie.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    @Override
    public Page<ProfileDto> findAll(Pageable pageRequest) {
        return profileRepository.findAll(pageRequest)
                .map(profileMapper::profileToProfileDto);
    }

    @Override
    public ProfileDto getProfile(Long id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return profileMapper.profileToProfileDto(profile);
    }

    @Override
    public ProfileDto registerProfile(ProfileDto profileDto) {
        Profile profile = profileMapper.profileDtoToProfile(profileDto);
        profileRepository.save(profile);
        return profileMapper.profileToProfileDto(profile);
    }

    @Override
    public ProfileDto updateProfile(ProfileDto profileDto, Long id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Profile updatedProfile = update(profile, profileMapper.profileDtoToProfile(profileDto));

        profileRepository.save(updatedProfile);
        return profileMapper.profileToProfileDto(updatedProfile);
    }

    @Override
    public void deleteProfile(Long id) {
        profileRepository.deleteById(id);
    }

    @Override
    public Profile getProfileByEmail(String email) {
        return profileRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    private Profile update(Profile current, Profile updated) {
        current.setFirstName(updated.getFirstName());
        current.setLastName(updated.getLastName());
        current.setEmail(updated.getEmail());
        current.setPassword(updated.getPassword());
        current.setAdmin(updated.isAdmin());
        current.setWatchlist(updated.getWatchlist());
        current.setFavorites(updated.getFavorites());
        return current;
    }
}

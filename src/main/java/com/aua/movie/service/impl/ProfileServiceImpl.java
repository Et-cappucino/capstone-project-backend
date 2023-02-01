package com.aua.movie.service.impl;

import com.aua.movie.dto.ProfileDto;
import com.aua.movie.mapper.ProfileMapper;
import com.aua.movie.model.Profile;
import com.aua.movie.repository.ProfileRepository;
import com.aua.movie.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final ProfileMapper profileMapper;

    @Override
    public List<ProfileDto> findAll() {
        List<Profile> profiles = profileRepository.findAll();
        return profiles.stream()
                .map(profileMapper::profileToProfileDto)
                .collect(Collectors.toList());
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

package com.aua.movie.service.impl;

import com.aua.movie.model.Profile;
import com.aua.movie.model.enums.Genre;
import com.aua.movie.repository.ProfileRepository;
import com.aua.movie.service.FavoriteGenresService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class FavoriteGenresServiceImpl implements FavoriteGenresService {

    private final ProfileRepository profileRepository;

    @Override
    public void addToFavoriteGenres(Genre genre, Long profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Set<Genre> favoriteGenres = profile.getFavoriteGenres();
        favoriteGenres.add(genre);
        profileRepository.save(profile);
    }

    @Override
    public void removeFromFavoriteGenres(Genre genre, Long profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        Set<Genre> favoriteGenres = profile.getFavoriteGenres();
        favoriteGenres.remove(genre);
        profileRepository.save(profile);
    }

    @Override
    public Set<Genre> getProfileFavoriteGenres(Long profileId) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return profile.getFavoriteGenres();
    }
}

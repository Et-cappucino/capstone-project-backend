package com.aua.movie.service;

import com.aua.movie.model.Profile;

import java.util.List;

public interface ProfileService {

    void addToWatchlist(Long watchableId, Long profileId);

    List<Profile> findAll();

    Profile getProfile(Long id);

    Profile registerProfile(Profile profile);

    void updateProfile(Profile profile);

    void deleteProfile(Long id);
}

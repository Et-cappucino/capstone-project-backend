package com.aua.movie.service;

import com.aua.movie.model.enums.Genre;

import java.util.Set;

public interface FavoriteGenresService {

    void addToFavoriteGenres(Set<Genre> genres, Long profileId);

    void removeFromFavoriteGenres(Set<Genre> genres, Long profileId);

    Set<Genre> getProfileFavoriteGenres(Long profileId);
}


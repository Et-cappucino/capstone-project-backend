package com.aua.movie.service;

import com.aua.movie.dto.WatchableDto;
import com.aua.movie.model.enums.Genre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchService {

    Page<WatchableDto> searchWatchable(String query, Pageable pageRequest);

    Page<WatchableDto> searchWatchableByGenre(Genre genre, Pageable pageRequest);

    Page<WatchableDto> searchWatchableByReleaseYear(Integer releaseYear, Pageable pageRequest);

    Page<WatchableDto> searchMovieByGenre(Genre genre, Pageable pageRequest);

    Page<WatchableDto> searchSeriesByGenre(Genre genre, Pageable pageRequest);

    Page<WatchableDto> searchMovieReleaseYear(Integer releaseYear, Pageable pageRequest);

    Page<WatchableDto> searchSeriesByReleaseYear(Integer releaseYear, Pageable pageRequest);
}

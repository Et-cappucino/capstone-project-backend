package com.aua.movie.service.impl;

import com.aua.movie.dto.WatchableDto;
import com.aua.movie.mapper.WatchableMapper;
import com.aua.movie.model.Profile;
import com.aua.movie.model.SearchRecord;
import com.aua.movie.model.enums.Genre;
import com.aua.movie.model.enums.WatchableType;
import com.aua.movie.repository.ProfileRepository;
import com.aua.movie.repository.SearchRecordRepository;
import com.aua.movie.repository.WatchableRepository;
import com.aua.movie.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final WatchableRepository watchableRepository;
    private final WatchableMapper watchableMapper;
    private final SearchRecordRepository searchRecordRepository;
    private final ProfileRepository profileRepository;

    @Override
    public Page<WatchableDto> searchWatchable(String query, Pageable pageRequest) {
        return search(query, pageRequest, null);
    }

    @Override
    public Page<WatchableDto> userSearchWatchable(String query, Pageable pageRequest, String profileEmail) {
        return search(query, pageRequest, profileEmail);
    }

    @Override
    public Page<WatchableDto> searchWatchableByGenre(Genre genre, Pageable pageRequest) {
        if (genre != null) {
            return watchableRepository.findByGenres(genre, pageRequest)
                    .map(watchableMapper::watchableToWatchableDto);
        } else {
            return null;
        }
    }

    @Override
    public Page<WatchableDto> searchWatchableByReleaseYear(Integer releaseYear, Pageable pageRequest) {
        if (releaseYear != 0) {
            LocalDate startDate = LocalDate.of(releaseYear, 1, 1);
            LocalDate endDate = LocalDate.of(releaseYear, 12, 31);
            return watchableRepository.findByReleaseDateBetween(startDate, endDate, pageRequest)
                    .map(watchableMapper::watchableToWatchableDto);
        } else {
            return null;
        }
    }

    @Override
    public Page<WatchableDto> searchMovieByGenre(Genre genre, Pageable pageRequest) {
        if (genre != null) {
            return watchableRepository.findByTypeAndGenres(WatchableType.MOVIE, genre, pageRequest)
                    .map(watchableMapper::watchableToWatchableDto);
        } else {
            return null;
        }
    }

    @Override
    public Page<WatchableDto> searchSeriesByGenre(Genre genre, Pageable pageRequest) {
        if (genre != null) {
            return watchableRepository.findByTypeAndGenres(WatchableType.SERIES, genre, pageRequest)
                    .map(watchableMapper::watchableToWatchableDto);
        } else {
            return null;
        }
    }

    @Override
    public Page<WatchableDto> searchMovieReleaseYear(Integer releaseYear, Pageable pageRequest) {
        if (releaseYear != 0) {
            LocalDate startDate = LocalDate.of(releaseYear, 1, 1);
            LocalDate endDate = LocalDate.of(releaseYear, 12, 31);
            return watchableRepository.findByTypeAndReleaseDateBetween(WatchableType.MOVIE, startDate, endDate, pageRequest)
                    .map(watchableMapper::watchableToWatchableDto);
        } else {
            return null;
        }
    }

    @Override
    public Page<WatchableDto> searchSeriesByReleaseYear(Integer releaseYear, Pageable pageRequest) {
        if (releaseYear != 0) {
            LocalDate startDate = LocalDate.of(releaseYear, 1, 1);
            LocalDate endDate = LocalDate.of(releaseYear, 12, 31);
            return watchableRepository.findByTypeAndReleaseDateBetween(WatchableType.SERIES,startDate, endDate, pageRequest)
                    .map(watchableMapper::watchableToWatchableDto);
        } else {
            return null;
        }
    }

    private Page<WatchableDto> search(String query, Pageable pageRequest, String profileEmail) {
        if (query != null && !query.isEmpty()) {
            if (profileEmail != null) {
                Profile profile = profileRepository.findByEmail(profileEmail)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
                SearchRecord searchRecord = SearchRecord.builder()
                        .createdAt(LocalDateTime.now())
                        .text(query)
                        .profile(profile)
                        .build();
                searchRecordRepository.save(searchRecord);
            }
            return watchableRepository.findByNameStartingWithIgnoreCase(query, pageRequest)
                    .map(watchableMapper::watchableToWatchableDto);
        } else {
            return null;
        }
    }
}
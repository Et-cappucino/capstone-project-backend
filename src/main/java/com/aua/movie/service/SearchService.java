package com.aua.movie.service;

import com.aua.movie.dto.WatchableDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchService {

    Page<WatchableDto> searchWatchable(String query, Pageable pageRequest);
}

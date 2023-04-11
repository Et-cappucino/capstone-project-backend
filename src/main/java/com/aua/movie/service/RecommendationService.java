package com.aua.movie.service;

import com.aua.movie.dto.WatchableDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RecommendationService {

    Page<WatchableDto> findAllRecommended(List<Integer> ids, Pageable pageRequest);
}

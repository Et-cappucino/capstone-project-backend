package com.aua.movie.service;

import com.aua.movie.dto.WatchableDto;
import com.aua.movie.model.enums.RecommendationType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RecommendationService {

    Page<WatchableDto> findAllRecommended(RecommendationType type, Long watchableId, Pageable pageRequest);
}

package com.aua.movie.service;

import com.aua.movie.dto.RateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RateService {

    RateDto rateWatchable(RateDto rateDto);

    Page<RateDto> getWatchableRates(Long watchableId, Pageable pageRequest);

    Page<RateDto> getProfileRates(Long profileId, Pageable pageRequest);
}

package com.aua.movie.service.impl;

import com.aua.movie.config.PageableHelper;
import com.aua.movie.dto.RateDto;
import com.aua.movie.mapper.RateMapper;
import com.aua.movie.model.Profile;
import com.aua.movie.model.Rate;
import com.aua.movie.model.Watchable;
import com.aua.movie.repository.ProfileRepository;
import com.aua.movie.repository.RateRepository;
import com.aua.movie.repository.WatchableRepository;
import com.aua.movie.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RateServiceImpl implements RateService {

    private final WatchableRepository watchableRepository;
    private final ProfileRepository profileRepository;
    private final RateRepository rateRepository;
    private final RateMapper rateMapper;
    private final PageableHelper pageableHelper;

    @Override
    public RateDto rateWatchable(RateDto rateDto) {
        Rate rate = rateMapper.rateDtoToRate(rateDto);
        List<Rate> profileRates = rate.getProfile().getRates();
        boolean isAlreadyRated = profileRates.stream()
                .anyMatch(currentRate -> currentRate.getWatchable().getId().equals(rate.getWatchable().getId()));
        return isAlreadyRated ?  null : rateMapper.rateToRateDto(rateRepository.save(rate));
    }

    @Override
    public Page<RateDto> getWatchableRates(Long watchableId, Pageable pageRequest) {
        Watchable watchable = watchableRepository.findById(watchableId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<RateDto> rateDtos = watchable.getRates().stream()
                .map(rateMapper::rateToRateDto)
                .collect(Collectors.toList());
        return pageableHelper.listToPage(rateDtos, pageRequest.getPageNumber(), pageRequest.getPageSize());
    }

    @Override
    public Page<RateDto> getProfileRates(Long profileId, Pageable pageRequest) {
        Profile profile = profileRepository.findById(profileId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<RateDto> rateDtos = profile.getRates().stream()
                .map(rateMapper::rateToRateDto)
                .collect(Collectors.toList());
        return pageableHelper.listToPage(rateDtos, pageRequest.getPageNumber(), pageRequest.getPageSize());
    }
}

package com.aua.movie.mapper;

import com.aua.movie.dto.RateDto;
import com.aua.movie.model.Profile;
import com.aua.movie.model.Rate;
import com.aua.movie.model.Watchable;
import com.aua.movie.repository.ProfileRepository;
import com.aua.movie.repository.WatchableRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Mapper(componentModel = "spring")
public abstract class RateMapper {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private WatchableRepository watchableRepository;

    public RateDto rateToRateDto(Rate rate) {
        return RateDto.builder()
                .id(rate.getId())
                .profileId(rate.getProfile().getId())
                .watchableId(rate.getWatchable().getId())
                .questionOneValue(rate.getQuestionOneValue())
                .questionTwoValue(rate.getQuestionTwoValue())
                .questionThreeValue(rate.getQuestionThreeValue())
                .ratedAt(rate.getRatedAt())
                .build();
    }

    public Rate rateDtoToRate(RateDto rateDto) {
        Profile profile = profileRepository.findById(rateDto.getProfileId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Watchable watchable = watchableRepository.findById(rateDto.getWatchableId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Rate rate = new Rate();
        rate.setId(rate.getId());
        rate.setQuestionOneValue(rateDto.getQuestionOneValue());
        rate.setQuestionTwoValue(rateDto.getQuestionTwoValue());
        rate.setQuestionThreeValue(rateDto.getQuestionThreeValue());
        rate.setRatedAt(rateDto.getRatedAt());
        rate.setProfile(profile);
        rate.setWatchable(watchable);
        return rate;
    }
}

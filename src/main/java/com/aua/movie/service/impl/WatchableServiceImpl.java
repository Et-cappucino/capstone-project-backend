package com.aua.movie.service.impl;

import com.aua.movie.dto.WatchableDto;
import com.aua.movie.mapper.WatchableMapper;
import com.aua.movie.model.Watchable;
import com.aua.movie.repository.WatchableRepository;
import com.aua.movie.service.WatchableService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class WatchableServiceImpl implements WatchableService {

    private final WatchableRepository watchableRepository;
    private final WatchableMapper watchableMapper;

    @Override
    public Page<WatchableDto> findAll(Pageable pageRequest) {
        return watchableRepository.findAll(pageRequest)
                .map(watchableMapper::watchableToWatchableDto);
    }

    @Override
    public WatchableDto getWatchable(Long id) {
        Watchable watchable = watchableRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return watchableMapper.watchableToWatchableDto(watchable);
    }

    @Override
    public WatchableDto registerWatchable(WatchableDto watchableDto) {
        Watchable watchable = watchableMapper.watchableDtoToWatchable(watchableDto);
        watchableRepository.save(watchable);
        return watchableMapper.watchableToWatchableDto(watchable);
    }

    @Override
    public WatchableDto updateWatchable(WatchableDto watchableDto, Long id) {
        Watchable watchable = watchableRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Watchable updatedWatchable = update(watchable, watchableMapper.watchableDtoToWatchable(watchableDto));

        watchableRepository.save(updatedWatchable);
        return watchableMapper.watchableToWatchableDto(updatedWatchable);
    }

    @Override
    public void deleteWatchable(Long id) {
        watchableRepository.deleteById(id);
    }

    private Watchable update(Watchable current, Watchable updated) {
        current.setName(updated.getName());
        current.setType(updated.getType());
        current.setDuration(updated.getDuration());
        current.setRating(updated.getRating());
        current.setVoteCount(updated.getVoteCount());
        current.setTrailerLink(updated.getTrailerLink());
        current.setPosterPath(updated.getPosterPath());
        current.setMainBackdropPath(updated.getMainBackdropPath());
        current.setBackdropPaths(updated.getBackdropPaths());
        current.setReleaseDate(updated.getReleaseDate());
        current.setDescription(updated.getDescription());
        current.setGenres(updated.getGenres());
        current.setCast(updated.getCast());
        return current;
    }
}

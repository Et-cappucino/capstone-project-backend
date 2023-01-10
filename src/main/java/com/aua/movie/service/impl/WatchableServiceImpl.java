package com.aua.movie.service.impl;

import com.aua.movie.repository.WatchableRepository;
import com.aua.movie.service.WatchableService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WatchableServiceImpl implements WatchableService {

    private final WatchableRepository watchableRepository;
}

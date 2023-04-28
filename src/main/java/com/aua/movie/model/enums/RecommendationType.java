package com.aua.movie.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RecommendationType {

    GENRE_BASED("/genre_based"),
    CONTENT_BASED("/content_based");

    private final String path;
}

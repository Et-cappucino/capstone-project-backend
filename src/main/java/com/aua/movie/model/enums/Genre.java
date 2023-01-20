package com.aua.movie.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Genre {

    ACTION(0, "Action"),
    ADVENTURE(1, "Adventure"),
    ANIMATION(2, "Animation"),
    COMEDY(3, "Comedy"),
    CRIME(4, "Crime"),
    DOCUMENTARY(5, "Documentary"),
    DRAMA(6, "Drama"),
    FAMILY(7, "Family"),
    FANTASY(8, "Fantasy"),
    HISTORY(9, "History"),
    HORROR(10, "Horror"),
    MUSIC(11, "Music"),
    MYSTERY(12, "Mystery"),
    ROMANCE(13, "Romance"),
    SCIENCE_FICTION(14, "Science Fiction"),
    TV_MOVIE(15, "TV-Movie"),
    THRILLER(16, "Thriller"),
    WAR(17, "Wer"),
    WESTERN(18, "Western");

    private final int id;
    private final String name;
}

package com.aua.movie.dto;

import com.aua.movie.model.enums.Genre;
import com.aua.movie.model.enums.WatchableType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Represents an info which is required to create a Watchable")
public class WatchableDto {

    @ApiModelProperty(value = "watchableId")
    private Long id;

    @ApiModelProperty(value = "Name of the watchable")
    @NotBlank
    private String name;

    @ApiModelProperty(value = "Type of the watchable")
    @NotNull
    private WatchableType type;

    @ApiModelProperty(value = "The release date of the watchable")
    private LocalDate releaseDate;

    @ApiModelProperty(value = "Description of the watchable")
    @NotBlank
    private String description;

    @ApiModelProperty(value = "Trailer link of the watchable")
    private String trailerLink;

    @ApiModelProperty(value = "Rating of the watchable")
    @Min(value = 0)
    @Max(value = 10)
    @Positive
    @NotNull
    private double rating;

    @ApiModelProperty(value = "Duration of the watchable")
    @Positive
    @NotNull
    private int duration;

    @ApiModelProperty(value = "Poster path of the watchable")
    private String posterPath;

    @ApiModelProperty(value = "List of all Backdrop image paths of the watchable")
    private List<String> backdropPaths;

    @ApiModelProperty(value = "Genres of the watchable")
    @Valid
    private List<Genre> genres;

    @ApiModelProperty(value = "Cast of the watchable")
    @Valid
    private List<ActorDto> cast;
}

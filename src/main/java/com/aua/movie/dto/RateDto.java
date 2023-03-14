package com.aua.movie.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Represents an info which is required to create a Rate")
public class RateDto {

    @ApiModelProperty(value = "rateId")
    private Long id;

    @ApiModelProperty(value = "Id of the profile who rates the watchable")
    @NotNull
    @Positive
    private Long profileId;

    @ApiModelProperty(value = "Id of the watchable under which the comment is posted")
    @NotNull
    @Positive
    private Long watchableId;

    @ApiModelProperty(value = "Rated value of the first question of the watchable")
    @Positive
    @Max(value = 5)
    @Min(value = 0)
    private Double questionOneValue;

    @ApiModelProperty(value = "Rated value of the second question of the watchable")
    @Positive
    @Max(value = 5)
    @Min(value = 0)
    private Double questionTwoValue;

    @ApiModelProperty(value = "Rated value of the third question of the watchable")
    @Positive
    @Max(value = 5)
    @Min(value = 0)
    private Double questionThreeValue;

    @ApiModelProperty(value = "The exact time the watchable was rated")
    private LocalDateTime ratedAt = LocalDateTime.now();
}

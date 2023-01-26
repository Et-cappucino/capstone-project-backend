package com.aua.movie.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Represents an info which is required to create an Actor")
public class ActorDto {

    @ApiModelProperty(value = "actorId")
    private Long id;

    @ApiModelProperty(value = "First name of the actor")
    @NotBlank
    private String firstName;

    @ApiModelProperty(value = "Last name of the actor")
    @NotBlank
    private String lastName;

    @ApiModelProperty(value = "Birth date of the actor")
    @Past
    private LocalDate birthDate;

    @ApiModelProperty(value = "Bio of the actor")
    private String bio;
}

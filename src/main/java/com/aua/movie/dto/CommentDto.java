package com.aua.movie.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Represents an info which is required to create a Comment")
public class CommentDto {

    @ApiModelProperty(value = "commentId")
    private Long id;

    @ApiModelProperty(value = "Text of the comment")
    @NotBlank
    private String text;

    @ApiModelProperty(value = "Id of the author of the comment")
    @NotNull
    @Positive
    private Long commenterId;

    @ApiModelProperty(value = "Id of the watchable under which the comment is posted")
    @NotNull
    @Positive
    private Long watchableId;

    @ApiModelProperty(value = "The exact time the comment was posted")
    private LocalDateTime createdAt = LocalDateTime.now();

    @ApiModelProperty(value = "Full name of the author of the comment")
    private String commenterFullName;
}
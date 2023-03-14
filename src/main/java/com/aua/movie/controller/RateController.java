package com.aua.movie.controller;

import com.aua.movie.dto.RateDto;
import com.aua.movie.service.RateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@Api(value = "Rate service rest API")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rates")
public class RateController {

    private final RateService rateService;

    @ApiOperation(value = "Rate a Watchable by providing a Rate body with profileID and watchableID", response = RateDto.class, tags = "rate-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @PostMapping
    public ResponseEntity<RateDto> rateWatchable(@RequestBody @Valid RateDto rateDto) {
        RateDto body = rateService.rateWatchable(rateDto);
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Get all Rates of a particular Watchable by ID with pagination support", tags = "rate-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping("/watchable-{watchableId}")
    public ResponseEntity<Page<RateDto>> getWatchableAllRates(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                              @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                                                              @PathVariable Long watchableId) {
        Page<RateDto> body = rateService.getWatchableRates(watchableId, PageRequest.of(pageNumber, pageSize));
        return ResponseEntity.ok(body);
    }

    @ApiOperation(value = "Get all Rates done by a particular Profile by ID with pagination support", tags = "rate-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping("/profile-{profileId}")
    public ResponseEntity<Page<RateDto>> getProfileAllRates(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                            @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                                                            @PathVariable Long profileId) {
        Page<RateDto> body = rateService.getProfileRates(profileId, PageRequest.of(pageNumber, pageSize));
        return ResponseEntity.ok(body);
    }
}

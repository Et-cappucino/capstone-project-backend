package com.aua.movie.controller;

import com.aua.movie.dto.WatchableDto;
import com.aua.movie.service.SearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Search Watchables service rest API")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/search")
public class SearchController {

    private final SearchService searchService;

    @ApiOperation(value = "Get watchables by search with pagination and sorting support", tags = "search-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping
    public ResponseEntity<Page<WatchableDto>> searchWatchables(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
                                                               @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                                                               @RequestParam(name = "sort", defaultValue = "rating") String sort,
                                                               @RequestParam(value = "query", required = false) String query) {
        Page<WatchableDto> body = searchService.searchWatchable(query, PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, sort)));
        return ResponseEntity.ok(body);
    }
}

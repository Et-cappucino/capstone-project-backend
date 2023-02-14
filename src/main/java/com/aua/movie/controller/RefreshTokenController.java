package com.aua.movie.controller;

import com.aua.movie.service.RefreshTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Api(value = "Token refreshing service rest API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/token/refresh")
public class RefreshTokenController {

    private final RefreshTokenService refreshTokenService;

    @ApiOperation(value = "Refresh the expired token", tags = "refresh-token-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping
    public ResponseEntity<Void> refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        refreshTokenService.refreshToken(request, response);
        return ResponseEntity.ok().build();
    }
}

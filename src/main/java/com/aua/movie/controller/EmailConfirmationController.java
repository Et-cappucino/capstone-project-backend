package com.aua.movie.controller;

import com.aua.movie.service.EmailConfirmationTokenService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/profile")
public class EmailConfirmationController {

    private final EmailConfirmationTokenService emailConfirmationTokenService;

    @ApiOperation(value = "Confirm the registered profile by email", tags = "email-confirmation-controller")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"), @ApiResponse(code = 400, message = "Bad Request")})
    @GetMapping("/confirm")
    public String confirm(@RequestParam String token) {
        emailConfirmationTokenService.confirmToken(token);
        return "email-confirmed";
    }
}

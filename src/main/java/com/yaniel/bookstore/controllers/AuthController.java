package com.yaniel.bookstore.controllers;


import com.yaniel.bookstore.models.entities.RefreshToken;
import com.yaniel.bookstore.models.entities.User;
import com.yaniel.bookstore.models.payload.auth.LoginDto;
import com.yaniel.bookstore.models.payload.auth.RefreshTokenDto;
import com.yaniel.bookstore.models.payload.users.CreatedUserDto;
import com.yaniel.bookstore.models.response.JWTAuthResponse;
import com.yaniel.bookstore.security.JwtTokenProvider;
import com.yaniel.bookstore.security.annotation.RateLimited;
import com.yaniel.bookstore.service.AuthService;
import com.yaniel.bookstore.service.RefreshTokenService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;
    private final JwtTokenProvider tokenProvider;

    public AuthController(AuthService authService,
                          RefreshTokenService refreshTokenService,
                          JwtTokenProvider tokenProvider) {
        this.authService = authService;
        this.refreshTokenService = refreshTokenService;
        this.tokenProvider = tokenProvider;
    }


    // Build Login REST API
    @RateLimited(limit = 5, duration = 60)
    @PostMapping(value = {"/login", "/signin"})
    public ResponseEntity<JWTAuthResponse> login(@RequestBody LoginDto loginDto){
        //1-login
        Authentication authentication  = authService.authenticate(loginDto);
        //2-Generar token
        String accessToken = tokenProvider.generateToken(authentication);
        //3-Generar Refresh Token
        Long userId = authService.getUserId(authentication);
        RefreshToken refreshToken = refreshTokenService.issueInitialToken(userId);
        //4-Respuesta
        JWTAuthResponse response = new JWTAuthResponse();
        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken.getToken());
        response.setTokenType("Bearer");

        return ResponseEntity.ok(response);
    }

    // Build Register REST API
    @PostMapping(value = {"/signup"})
    public ResponseEntity<String> register(@RequestBody @Valid CreatedUserDto registerDto){
        String response = authService.register(registerDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // REFRESH TOKEN
    @PostMapping("/refresh")
    public ResponseEntity<JWTAuthResponse> refresh(@RequestBody RefreshTokenDto request) {

        // 1. Rotar refresh token
        RefreshToken newRefresh = refreshTokenService.rotateToken(request.getRefreshToken());

        // 2. Construir Authentication desde el usuario
        User user = newRefresh.getUser();
        Authentication auth = authService.buildAuthentication(user);

        // 3. Generar nuevo access token
        String newAccessToken = tokenProvider.generateToken(auth);

        // 4. Respuesta
        JWTAuthResponse response = new JWTAuthResponse();
        response.setAccessToken(newAccessToken);
        response.setRefreshToken(newRefresh.getToken());
        response.setTokenType("Bearer");

        return ResponseEntity.ok(response);
    }

    // LOGOUT
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody RefreshTokenDto request) {

        RefreshToken refreshToken = refreshTokenService.findByToken(request.getRefreshToken());
        refreshTokenService.revokeFamily(refreshToken.getTokenFamily());

        return ResponseEntity.ok("Logout exitoso");
    }

}
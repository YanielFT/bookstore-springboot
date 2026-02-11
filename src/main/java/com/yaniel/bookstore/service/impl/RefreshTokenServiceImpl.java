package com.yaniel.bookstore.service.impl;

import com.yaniel.bookstore.errors.exception.ApiException;
import com.yaniel.bookstore.models.entities.RefreshToken;
import com.yaniel.bookstore.models.entities.User;
import com.yaniel.bookstore.repository.RefreshTokenRepository;
import com.yaniel.bookstore.repository.UserRepository;
import com.yaniel.bookstore.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Value("${app.jwt-refresh-expiration-milliseconds:604800000}")
    private long refreshTokenExpiration;

    @Override
    public RefreshToken issueInitialToken(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,"Usuario no encontrado"));

        String family = UUID.randomUUID().toString();
        return createToken(user, family);

    }

    @Override
    public RefreshToken rotateToken(String rawToken) {
        RefreshToken old = refreshTokenRepository.findByToken(rawToken)
                .orElseThrow(() -> new ApiException(HttpStatus.FORBIDDEN,"Refresh token no válido"));

        if (old.isExpired() || old.isRevoked() || old.isUsed()) {
            // posible robo → revocar toda la familia
            revokeFamily(old.getTokenFamily());
            throw new ApiException(HttpStatus.FORBIDDEN,"Refresh token inválido o comprometido");
        }

        old.setUsed(true);
        refreshTokenRepository.save(old);

        return createToken(old.getUser(), old.getTokenFamily());
    }

    @Override
    public void revokeFamily(String family) {
        refreshTokenRepository.revokeAllByTokenFamily(family);
    }

    @Override
    public RefreshToken createToken(User user, String family) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setTokenFamily(family);
        refreshToken.setExpiryDate(
                LocalDateTime.now().plusSeconds(refreshTokenExpiration / 1000)
        );
        refreshToken.setRevoked(false);
        refreshToken.setUsed(false);
        return refreshTokenRepository.save(refreshToken);

    }

    @Override
    public void revokeAllTokensForUser(Long userId) {
        RefreshToken refreshToken = refreshTokenRepository.findByUserId(userId).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND,"Usuario no encontrado"));
        refreshTokenRepository.revokeAllByTokenFamily(refreshToken.getTokenFamily());
    }

    @Override
    public RefreshToken findByToken(String token){
       return refreshTokenRepository.findByToken(token).orElseThrow(() -> new ApiException(HttpStatus.UNAUTHORIZED,"Refresh token no válido"));
    }
}

package com.yaniel.bookstore.service;

import com.yaniel.bookstore.models.entities.RefreshToken;
import com.yaniel.bookstore.models.entities.User;

public interface RefreshTokenService {
    RefreshToken issueInitialToken(Long userId);
    RefreshToken rotateToken(String rawToken);
    void revokeFamily(String family);
    RefreshToken createToken(User user, String family);

    void revokeAllTokensForUser(Long userId);

    RefreshToken findByToken(String token);
}

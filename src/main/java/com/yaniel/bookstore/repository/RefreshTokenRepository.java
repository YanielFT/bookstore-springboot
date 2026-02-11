package com.yaniel.bookstore.repository;

import com.yaniel.bookstore.models.entities.RefreshToken;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
    Optional<RefreshToken> findByToken(String token);
    Optional<RefreshToken> findByUserId(Long userId);
    void deleteByUserId(Long userId);
    List<RefreshToken> findAllByTokenFamily(String tokenFamily);
    @Transactional
    @Modifying
    @Query("update RefreshToken rt set rt.revoked = true where rt.tokenFamily = :family")
    void revokeAllByTokenFamily(@Param("family") String family);

}

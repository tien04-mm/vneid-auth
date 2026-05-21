package com.thanglong.vneid.infrastructure.adapter.persistence.jpa;

import com.thanglong.vneid.infrastructure.adapter.persistence.entity.QrLoginSessionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QrLoginSessionJpaRepository extends JpaRepository<QrLoginSessionEntity, String> {

    Optional<QrLoginSessionEntity> findByQrToken(String qrToken);

    void deleteByQrToken(String qrToken);

    @Modifying
    @Query("DELETE FROM QrLoginSessionEntity q WHERE q.expiresAt < CURRENT_TIMESTAMP")
    void deleteExpiredSessions();
}

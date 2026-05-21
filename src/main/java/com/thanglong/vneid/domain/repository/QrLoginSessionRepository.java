package com.thanglong.vneid.domain.repository;

import com.thanglong.vneid.domain.model.QrLoginSession;

import java.util.Optional;

/**
 * Cổng giao tiếp dữ liệu cho QrLoginSession.
 */
public interface QrLoginSessionRepository {
    Optional<QrLoginSession> findByQrToken(String qrToken);

    QrLoginSession save(QrLoginSession qrLoginSession);

    void deleteByQrToken(String qrToken);

    void deleteExpiredSessions();
}
